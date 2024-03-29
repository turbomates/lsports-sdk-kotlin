package com.turbomates.kotlin.lsports.sdk.listener

import com.rabbitmq.client.Channel
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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory

internal class DeliverCallbackListener(
    private val messagesQueues: MutableMap<Long, MutableList<MessageData>>,
    private val handler: Handler,
    private val channel: Channel,
    context: String,
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
) : CoroutineScope by CoroutineScope(dispatcher), DeliverCallback {
    //TODO: temporary fix for jitpack io kotlin version
    private val logger = LoggerFactory.getLogger("com.turbomates.kotlin.lsports.sdk.listener.$context")
    override fun handle(consumerTag: String?, delivery: Delivery) {
        val deliveryTag = delivery.envelope.deliveryTag
        val deliveryBody = String(delivery.body)
        logger.debug("$consumerTag receive deliveryTag: $deliveryTag")
        try {
            Json.decodeFromString(MessageSerializer, deliveryBody).let { message ->
                logger.debug("{} receive message id: {}", consumerTag, message.header.msgGuid)
                when (message) {
                    is FixtureUpdateMessage -> asyncHandleOrderedEvents(message, deliveryBody, deliveryTag)
                    is LivescoreUpdateMessage -> async(message, deliveryBody, deliveryTag)
                    is MarketUpdateMessage -> asyncHandleOrderedEvents(message, deliveryBody, deliveryTag)
                    is SettlementsMessage -> asyncHandleOrderedEvents(message, deliveryBody, deliveryTag)
                    is KeepAliveMessage -> async(message, deliveryBody, deliveryTag)
                    is HeartbeatMessage -> async(message, deliveryBody, deliveryTag)
                    else -> throw MessageSerializer.UnimplementedMessageTypeException(
                        Message.Type.entries.first { it.value == message.header.type.value }
                    )
                }
            }
        } catch (ex: MessageSerializer.UnimplementedMessageTypeException) {
            logger.debug("Listener <$consumerTag> ignored message. Error: ${ex.message}; Message: $deliveryBody")
            if (channel.isOpen) {
                channel.basicAck(deliveryTag, false)
            }
        } catch (logging: Throwable) {
            logger.error("Listener <$consumerTag> was cancelled. Error: ${logging.message}; Message: $deliveryBody")
            if (channel.isOpen) {
                channel.basicNack(deliveryTag, false, true)
            }
            throw logging
        }
    }

    private fun async(message: Message, deliveryBody: String, deliveryTag: Long) = launch {
        handle(message, deliveryBody, deliveryTag)
    }

    private suspend fun handle(message: Message, deliveryBody: String, deliveryTag: Long) {
        try {
            coroutineScope {
                when (message) {
                    is FixtureUpdateMessage -> handler.handle(message)
                    is LivescoreUpdateMessage -> handler.handle(message)
                    is MarketUpdateMessage -> handler.handle(message)
                    is SettlementsMessage -> handler.handle(message)
                    is KeepAliveMessage -> handler.handle(message)
                    is HeartbeatMessage -> handler.handle(message)
                    else -> throw MessageSerializer.UnimplementedMessageTypeException(
                        Message.Type.entries.first { it.value == message.header.type.value }
                    )
                }
                if (channel.isOpen) {
                    channel.basicAck(deliveryTag, false)
                }
            }
        } catch (transient: Throwable) {
            logger.error("Error message $deliveryBody handling", transient)
            if (channel.isOpen) {
                channel.basicNack(deliveryTag, false, true)
            }
        }
    }

    private fun asyncHandleOrderedEvents(message: EventsMessage, deliveryBody: String, deliveryTag: Long) {
        val fixtureIds = message.body.events.map { it.fixtureId }
        if (fixtureIds.isNotEmpty()) {
            val messageData = MessageData(message, deliveryTag, deliveryBody)
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
            handle(it.message, it.deliveryBody, it.deliveryTag)
            queue.remove(it)
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

internal data class MessageData(val message: EventsMessage, val deliveryTag: Long, val deliveryBody: String)
