package com.turbomates.kotlin.lsports.sdk.client

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import java.io.File
import org.slf4j.LoggerFactory

class Consumer<Message>(
    private val handler: Handler<Message>,
    private val connection: Connection
) {
    fun consume(queueName: String) {
        val channel = connection.createChannel()
        channel.basicConsume(
            queueName,
            false,
            DeliverCallbackListener(channel, handler),
            CancelCallbackListener()
        )
    }
}

private class DeliverCallbackListener<Message>(
    private val channel: Channel,
    private val handler: Handler<Message>,
): DeliverCallback {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Suppress("UNCHECKED_CAST")
    override fun handle(consumerTag: String?, message: Delivery) {
        try {
            handler.handle(String(message.body) as Message)
            logger.info("Event was accepted $consumerTag")
            channel.basicAck(message.envelope.deliveryTag, false)
        } catch (logging: Throwable) {
            logger.error("Listener was cancelled $consumerTag. Message ${logging.message}")
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
