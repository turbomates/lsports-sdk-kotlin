package com.turbomates.kotlin.lsports.sdk.listener

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.Connection
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import com.turbomates.kotlin.lsports.sdk.serializer.MessageSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.channels.ClosedSendChannelException
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.isActive
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.Closeable
import kotlinx.coroutines.channels.Channel as KChannel

class Consumer(
    private val handler: Handler,
    connection: Connection,
    queue: String,
    private val prefetchSize: Int
) : CoroutineScope by CoroutineScope(Dispatchers.IO), Closeable {

    private val logger = LoggerFactory.getLogger(javaClass)

    private val deliveries = KChannel<Delivery>()

    private val consumerTag: String

    private val channel = connection.createChannel()

    init {
        channel.basicQos(prefetchSize, false)
        consumerTag = channel.basicConsume(
            queue, false,
            DeliverCallbackListener(deliveries, logger),
            CancelCallbackListener(deliveries, logger)
        )
    }

    private suspend fun consumeOne() {
        try {
            val delivery = deliveries.receive()
            val deliveryTag = delivery.envelope.deliveryTag

            try {
                val message = Json.decodeFromString(MessageSerializer, String(delivery.body))
                handler.handle(message)
                channel.basicAck(deliveryTag, false)
            } catch (logging: Throwable) {
                logger.error("Listener was cancelled $consumerTag. Message ${logging.message}")
                channel.basicNack(delivery.envelope.deliveryTag, false, true)
            }
        } catch (e: Exception) {
            when (e) {
                is ClosedReceiveChannelException -> throw Exception("Cancel exception")
                else -> throw e
            }
        }
    }

    suspend fun consume() = coroutineScope {
        while (isActive) {
            (1..prefetchSize).map {
                async(coroutineContext) { consumeOne() }
            }.awaitAll()
        }
    }

    override fun close() {
        logger.debug("closing Consumer")
        channel.basicCancel(consumerTag)
        deliveries.cancel()
    }
}

private class DeliverCallbackListener(
    val deliveries: KChannel<Delivery>,
    private val logger: Logger
) : DeliverCallback {
    override fun handle(consumerTag: String?, delivery: Delivery) {
        try {
            deliveries.trySendBlocking(delivery)
        } catch (e: ClosedSendChannelException) {
            logger.debug("Can't receive a message. Consumer $consumerTag has been cancelled")
        }
    }
}

private class CancelCallbackListener(val deliveries: KChannel<Delivery>, private val logger: Logger) : CancelCallback {
    override fun handle(consumerTag: String?) {
        logger.error("Listener was cancelled $consumerTag")
        deliveries.cancel()
    }
}
