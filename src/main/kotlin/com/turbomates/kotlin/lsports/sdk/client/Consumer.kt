package com.turbomates.kotlin.lsports.sdk.client

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.Connection
import com.rabbitmq.client.DeliverCallback
import org.slf4j.LoggerFactory

class Consumer(
    private val listener: Listener,
    private val connection: Connection
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    private val deliveryCallback = DeliverCallback { consumerTag, message ->
        listener.handle(String(message.body))
        logger.info("Event was accepted $consumerTag")
    }
    private val cancelCallback = CancelCallback { consumerTag ->
        logger.error("Listener was cancelled $consumerTag")
    }

    fun consume(queueName: String) {
        connection
            .createChannel()
            .basicConsume(
                queueName,
                false,
                deliveryCallback,
                cancelCallback
            )
    }
}
