package com.turbomates.kotlin.lsports.sdk.listener

import com.rabbitmq.client.ConnectionFactory

class Listener(
    private val connectionFactory: ConnectionFactory,
) {
    fun listen(handler: Handler, packageId: String) {
        Consumer(handler, connectionFactory.newConnection())
            .consume(packageId)
    }
}
