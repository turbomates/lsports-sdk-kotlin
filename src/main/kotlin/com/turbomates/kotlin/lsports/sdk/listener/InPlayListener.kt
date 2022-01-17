package com.turbomates.kotlin.lsports.sdk.listener

import com.rabbitmq.client.ConnectionFactory
import com.turbomates.kotlin.lsports.sdk.LSportsConfig

class InPlayListener(
    private val config: LSportsConfig,
    private val connectionFactory: ConnectionFactory
) {
    fun listenLive(handler: Handler, packageId: String) {
        connectionFactory.host = config.liveHost
        Consumer(handler, connectionFactory.newConnection())
            .consume(packageId)
    }
}