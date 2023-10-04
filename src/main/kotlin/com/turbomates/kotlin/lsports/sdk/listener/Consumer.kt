package com.turbomates.kotlin.lsports.sdk.listener

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import com.turbomates.kotlin.lsports.sdk.listener.message.EventsMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.FixtureUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.HeartbeatMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.KeepAliveMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.LivescoreUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.MarketUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.Message
import com.turbomates.kotlin.lsports.sdk.listener.message.SettlementsMessage
import com.turbomates.kotlin.lsports.sdk.serializer.MessageSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.Closeable

class Consumer(
    private val handler: Handler,
    private val connection: Connection,
    private val queueName: String,
    private val prefetchSize: Int
) : Closeable {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val messagesQueues = mutableMapOf<Long, MutableList<MessageData>>()
    private val channel = connection.createChannel().apply {
        basicQos(prefetchSize, false)
    }

    private lateinit var consumerTag: String

    fun consume() {
        consumerTag = channel.basicConsume(
            queueName, false,
            DeliverCallbackListener(messagesQueues, handler, channel, logger),
            CancelCallbackListener(this, logger)
        )
    }

    override fun close() {
        try {
            if (channel.isOpen) {
                channel.close()
            }
        } finally {
            if (connection.isOpen) {
                connection.close()
            }
        }
    }
}

private class DeliverCallbackListener(
    private val messagesQueues: MutableMap<Long, MutableList<MessageData>>,
    private val handler: Handler,
    private val channel: Channel,
    private val logger: Logger
) : CoroutineScope by CoroutineScope(Dispatchers.Default), DeliverCallback {
    override fun handle(consumerTag: String?, delivery: Delivery) {
        val deliveryTag = delivery.envelope.deliveryTag
        val deliveryBody = String(delivery.body)
        try {
            Json.decodeFromString(MessageSerializer, deliveryBody).let { message ->
                when (message) {
                    is FixtureUpdateMessage -> asyncHandleWithEvents(message, deliveryTag)
                    is LivescoreUpdateMessage -> asyncHandleWithEvents(message, deliveryTag)
                    is MarketUpdateMessage -> asyncHandleWithEvents(message, deliveryTag)
                    is SettlementsMessage -> asyncHandleWithEvents(message, deliveryTag)
                    is KeepAliveMessage -> syncHandle(message, deliveryTag)
                    is HeartbeatMessage -> syncHandle(message, deliveryTag)
                    else -> throw MessageSerializer.UnimplementedMessageTypeException(
                        Message.Type.values().first { it.value == message.header.type.value }
                    )
                }
            }
        } catch (ex: MessageSerializer.UnimplementedMessageTypeException) {
            logger.error("Listener <$consumerTag> ignored message. Error: ${ex.message}; Message: $deliveryBody")
            if (channel.isOpen) {
                channel.basicAck(deliveryTag, false)
            }
        } catch (logging: Throwable) {
            logger.error("Listener <$consumerTag> was cancelled. Error: ${logging.message}; Message: $deliveryBody")
            if (channel.isOpen) {
                channel.basicNack(delivery.envelope.deliveryTag, false, true)
            }
            throw logging
        }
    }

    private fun syncHandle(message: Message, deliveryTag: Long) = launch {
        handle(message)
        if (channel.isOpen) {
            channel.basicAck(deliveryTag, false)
        }
    }

    private suspend fun handle(message: Message) = when (message) {
        is FixtureUpdateMessage -> handler.handle(message)
        is LivescoreUpdateMessage -> handler.handle(message)
        is MarketUpdateMessage -> handler.handle(message)
        is SettlementsMessage -> handler.handle(message)
        is KeepAliveMessage -> handler.handle(message)
        is HeartbeatMessage -> handler.handle(message)
        else -> throw MessageSerializer.UnimplementedMessageTypeException(
            Message.Type.values().first { it.value == message.header.type.value }
        )
    }

    private fun asyncHandleWithEvents(message: EventsMessage, deliveryTag: Long) {
        val fixtureIds = message.body.events.map { it.fixtureId }
        if (fixtureIds.isNotEmpty()) {
            val messageData = MessageData(message, deliveryTag)
            val existedQueueFixtureId = fixtureIds.find { messagesQueues[it] != null }
            if (existedQueueFixtureId != null) {
                messagesQueues[existedQueueFixtureId]?.add(messageData)
            } else {
                val queue = mutableListOf(messageData)
                fixtureIds.forEach { fixtureId ->
                    messagesQueues[fixtureId] = queue
                }
                launch { processMessagesQueue(fixtureIds, queue) }
            }
        }
    }

    private suspend fun processMessagesQueue(fixtureIds: List<Long>, queue: MutableList<MessageData>) {
        queue.firstOrNull()?.let {
            handle(it.message)
            queue.remove(it)
            if (channel.isOpen) {
                channel.basicAck(it.deliveryTag, false)
            }

            val currentFixtureIds = it.message.body.events.map { event -> event.fixtureId }
            val allFixtureIds = (fixtureIds + currentFixtureIds).toSet().toList()
            processMessagesQueue(allFixtureIds, queue)
        } ?: run {
            fixtureIds.forEach {
                messagesQueues.remove(it)
            }
        }
    }
}

private class CancelCallbackListener(
    private val consumer: Consumer,
    private val logger: Logger
) : CancelCallback {
    override fun handle(consumerTag: String?) {
        val message = "Listener was cancelled $consumerTag"
        logger.error(message)
        consumer.close()
        throw CancelCallbackListenerException(message)
    }

    private class CancelCallbackListenerException(message: String) : Exception(message)
}

private data class MessageData(val message: EventsMessage, val deliveryTag: Long)
