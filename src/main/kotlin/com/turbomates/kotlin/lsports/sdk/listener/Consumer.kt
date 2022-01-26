package com.turbomates.kotlin.lsports.sdk.listener

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.Connection
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import com.turbomates.kotlin.lsports.sdk.serializer.MessageSerializer
import java.io.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.channels.ClosedSendChannelException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory

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
        try {
            channel.basicCancel(consumerTag)
        } catch (expected: Exception) {
            if (expected is UninitializedPropertyAccessException)
                throw UninitializedPropertyAccessException("Consumer has not been initialized")

            throw expected
        }
    }

    private fun consumeDelivery(delivery: Delivery) {
        try {
            val deliveryTag = delivery.envelope.deliveryTag

            try {
                val message = Json.decodeFromString(MessageSerializer, String(delivery.body))
                handler.handle(message)
                channel.basicAck(deliveryTag, false)
            } catch (logging: Throwable) {
                logger.error("Listener was cancelled $consumerTag. Message ${logging.message}")
                channel.basicNack(delivery.envelope.deliveryTag, false, true)
            }
        } catch (expected: Exception) {
            if (expected is ClosedReceiveChannelException)
                throw ClosedReceiveChannelException("Cancel exception")

            throw expected
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
        }
    }
}

private class CancelCallbackListener(private val logger: Logger) :
    CancelCallback {
    override fun handle(consumerTag: String?) {
        logger.error("Listener was cancelled $consumerTag")
    }
}
