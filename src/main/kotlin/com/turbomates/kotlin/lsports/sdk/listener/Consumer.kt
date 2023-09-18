package com.turbomates.kotlin.lsports.sdk.listener

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.Connection
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import com.turbomates.kotlin.lsports.sdk.listener.message.FixtureUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.HeartbeatMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.KeepAliveMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.LivescoreUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.MarketUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.SettlementsMessage
import com.turbomates.kotlin.lsports.sdk.serializer.MessageSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ClosedSendChannelException
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.Closeable

class Consumer(
    private val handler: Handler,
    private val connection: Connection,
    private val queueName: String,
    private val prefetchSize: Int
) : Closeable, CoroutineScope by CoroutineScope(Dispatchers.IO) {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val queue = Channel<Delivery>(Channel.UNLIMITED)
    private val channel = connection.createChannel().apply {
        basicQos(prefetchSize, false)
    }

    private lateinit var consumerTag: String

    suspend fun consume() {
        consumerTag = channel.basicConsume(
            queueName, false,
            DeliverCallbackListener(queue, this, logger),
            CancelCallbackListener(this, logger)
        )

        for (delivery in queue) {
            consumeDelivery(delivery)
        }
    }

    override fun close() {
        channel.basicCancel(consumerTag)
        connection.close()
        queue.close()
    }

    @Suppress("TooGenericExceptionCaught")
    private suspend fun consumeDelivery(delivery: Delivery) {
        val deliveryTag = delivery.envelope.deliveryTag
        val deliveryBody = String(delivery.body)

        try {
            Json.decodeFromString(MessageSerializer, deliveryBody).let {
                when (it) {
                    is FixtureUpdateMessage -> handler.handle(it)
                    is LivescoreUpdateMessage -> handler.handle(it)
                    is MarketUpdateMessage -> handler.handle(it)
                    is KeepAliveMessage -> handler.handle(it)
                    is HeartbeatMessage -> handler.handle(it)
                    is SettlementsMessage -> handler.handle(it)
                }
            }

            channel.basicAck(deliveryTag, false)
        } catch (ex: MessageSerializer.UnimplementedMessageTypeException) {
            logger.error("Listener <$consumerTag> ignored message. Error: ${ex.message}; Message: $deliveryBody")
            channel.basicAck(deliveryTag, false)
        } catch (logging: Throwable) {
            logger.error("Listener <$consumerTag> was cancelled. Error: ${logging.message}; Message: $deliveryBody")
            channel.basicNack(delivery.envelope.deliveryTag, false, true)
            throw logging
        }
    }
}

private class DeliverCallbackListener(
    private val queue: Channel<Delivery>,
    private val consumer: Consumer,
    private val logger: Logger
) : CoroutineScope by CoroutineScope(Dispatchers.IO), DeliverCallback {
    override fun handle(consumerTag: String?, delivery: Delivery) {
        try {
            queue.trySend(delivery)
        } catch (expected: ClosedSendChannelException) {
            logger.debug("Can't receive a message. Consumer $consumerTag has been cancelled")
            consumer.close()
            throw expected
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
