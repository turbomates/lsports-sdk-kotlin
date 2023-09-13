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
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.channels.ClosedSendChannelException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.Closeable

class Consumer(
    private val handler: Handler,
    connection: Connection,
    private val queue: String,
    private val prefetchSize: Int
) : Closeable {

    private val logger = LoggerFactory.getLogger(javaClass)

    private val channel = connection.createChannel().apply {
        basicQos(prefetchSize, false)
    }

    private var deliveriesFlow: MutableSharedFlow<Delivery> = MutableSharedFlow(
        extraBufferCapacity = prefetchSize,
        onBufferOverflow = BufferOverflow.SUSPEND
    )

    private lateinit var consumerTag: String

    suspend fun consume() {
        consumerTag = channel.basicConsume(
            queue, false,
            DeliverCallbackListener(deliveriesFlow, logger),
            CancelCallbackListener(logger)
        )

        deliveriesFlow.collect {
            consumeDelivery(it)
        }
    }

    override fun close() {
        channel.basicCancel(consumerTag)
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
    val deliveriesFlow: MutableSharedFlow<Delivery>,
    private val logger: Logger
) : CoroutineScope by CoroutineScope(Dispatchers.IO), DeliverCallback {
    override fun handle(consumerTag: String?, delivery: Delivery) {
        try {
            deliveriesFlow.tryEmit(delivery)
        } catch (expected: ClosedSendChannelException) {
            logger.debug("Can't receive a message. Consumer $consumerTag has been cancelled")
            throw expected
        }
    }
}

private class CancelCallbackListener(private val logger: Logger) :
    CancelCallback {
    override fun handle(consumerTag: String?) {
        val message = "Listener was cancelled $consumerTag"
        logger.error(message)
        throw CancelCallbackListenerException(message)
    }

    private class CancelCallbackListenerException(message: String) : Exception(message)
}
