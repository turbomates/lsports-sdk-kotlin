package com.turbomates.kotlin.lsports.sdk.listener

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.Connection
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import com.turbomates.kotlin.lsports.sdk.serializer.MessageSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.channels.ClosedSendChannelException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.Closeable

class Consumer(
    private val handler: Handler,
    connection: Connection,
    queue: String,
    prefetchSize: Int
) : CoroutineScope by CoroutineScope(Dispatchers.IO), Closeable {

    private val logger = LoggerFactory.getLogger(javaClass)

    private val consumerTag: String

    private val channel = connection.createChannel()

    private val deliveriesFlow: MutableSharedFlow<Delivery> = MutableSharedFlow(extraBufferCapacity = prefetchSize)

    init {
        channel.basicQos(prefetchSize, false)
        consumerTag = channel.basicConsume(
            queue, false,
            DeliverCallbackListener(deliveriesFlow, logger),
            CancelCallbackListener(logger)
        )
    }

    suspend fun consume() {
        deliveriesFlow.collect { delivery ->
            try {
                val deliveryTag = delivery.envelope.deliveryTag
                delay((Math.random() *1000).toLong())
                try {
                    val message = Json.decodeFromString(MessageSerializer, String(delivery.body))
                    handler.handle(message)
                    channel.basicAck(deliveryTag, false)
                    delay((Math.random() *1000).toLong())
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
    }

    override fun close() {
        logger.debug("closing Consumer")
        channel.basicCancel(consumerTag)
    }
}

private class DeliverCallbackListener(
    val deliveriesFlow: MutableSharedFlow<Delivery>,
    private val logger: Logger
) : DeliverCallback {
    override fun handle(consumerTag: String?, delivery: Delivery) {
        try {
            runBlocking {
                deliveriesFlow.emit(delivery)
            }
        } catch (e: ClosedSendChannelException) {
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
