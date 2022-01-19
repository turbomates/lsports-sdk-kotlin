package com.turbomates.kotlin.lsports.sdk.listener

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import com.turbomates.kotlin.lsports.sdk.serializer.MessageSerializer
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory

class Consumer(
    private val handler: Handler,
    private val connection: Connection
) {
    fun consume(packageId: String) {
        val channel = connection.createChannel()
        channel.basicConsume(
            "_${packageId}_",
            false,
            DeliverCallbackListener(channel, handler),
            CancelCallbackListener()
        )
    }
}

private class DeliverCallbackListener(
    private val channel: Channel,
    private val handler: Handler,
): DeliverCallback {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun handle(consumerTag: String?, delivery: Delivery) {
        try {
            val message = Json.decodeFromString(MessageSerializer, String(delivery.body))
            handler.handle(message)
            logger.info("Event was accepted $consumerTag")
            channel.basicAck(delivery.envelope.deliveryTag, false)
        } catch (logging: Throwable) {
            logger.error("Listener was cancelled $consumerTag. Message ${logging.message}")
            channel.basicNack(delivery.envelope.deliveryTag, false, true)
        }
    }
}

private class CancelCallbackListener : CancelCallback {
    private val logger = LoggerFactory.getLogger(javaClass)
    override fun handle(consumerTag: String?) {
        logger.error("Listener was cancelled $consumerTag")
    }
}
