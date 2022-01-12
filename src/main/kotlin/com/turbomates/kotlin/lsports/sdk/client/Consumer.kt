package com.turbomates.kotlin.lsports.sdk.client

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import org.slf4j.LoggerFactory

class Consumer(
    private val listener: Listener,
    private val connection: Connection
) {
    fun consume(queueName: String) {
        val channel = connection.createChannel()
        channel.basicConsume(
            queueName,
            false,
            DeliverCallbackListener(channel, listener),
            CancelCallbackListener()
        )
    }
}

private class DeliverCallbackListener(
    private val channel: Channel,
    private val listener: Listener,
): DeliverCallback {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun handle(consumerTag: String?, message: Delivery) {
        try {
            listener.handle(String(message.body))
            logger.info("Event was accepted $consumerTag")
            channel.basicAck(message.envelope.deliveryTag, false)
        } catch (logging: Throwable) {
            logger.error("Listener was cancelled $consumerTag")
            channel.basicNack(message.envelope.deliveryTag, false, true)
        }
    }
}

private class CancelCallbackListener : CancelCallback {
    private val logger = LoggerFactory.getLogger(javaClass)
    override fun handle(consumerTag: String?) {
        logger.error("Listener was cancelled $consumerTag")
    }
}
