package com.turbomates.kotlin.lsports.sdk.listener

import com.rabbitmq.client.ConnectionFactory
import com.turbomates.kotlin.lsports.sdk.LSportsConfig

class PreMatchListener(
    private val config: LSportsConfig,
    private val connectionFactory: ConnectionFactory
) {
    fun listenPreLive(handler: Handler, packageId: String) {
        connectionFactory.host = config.preLiveHost
        Consumer(handler, connectionFactory.newConnection())
            .consume(packageId)
    }
}
