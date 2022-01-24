package com.turbomates.kotlin.lsports.sdk.listener

import com.rabbitmq.client.ConnectionFactory
import com.turbomates.kotlin.lsports.sdk.LSportsConfig

abstract class Listener(private val config: LSportsConfig) {
    protected val connectionFactory = ConnectionFactory().apply {
            port = config.port
            username = config.username
            password = config.password
            virtualHost = config.virtualHost
            requestedHeartbeat = config.requestHeartbeat
            networkRecoveryInterval = config.networkRecoveryInterval
            isAutomaticRecoveryEnabled = config.isAutomaticRecoveryEnabled
        }

    abstract suspend fun listen(handler: Handler, prefetchSize: Int = 1)
}
