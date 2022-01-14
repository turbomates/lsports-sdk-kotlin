package com.turbomates.kotlin.lsports.sdk.client.listener

import com.rabbitmq.client.ConnectionFactory
import com.turbomates.kotlin.lsports.sdk.client.LSportConfig
import com.turbomates.kotlin.lsports.sdk.client.infrastructure.PackageId

class LSportListener(
    private val config: LSportConfig
) {
    private val connectionFactory = configureRabbitMq(config)

    fun listenLive(handler: Handler, packageId: PackageId) {
        connectionFactory.host = config.liveHost
        Consumer(handler, connectionFactory.newConnection())
            .consume(packageId)
    }

    fun listenPreLive(handler: Handler, packageId: PackageId) {
        connectionFactory.host = config.preLiveHost
        Consumer(handler, connectionFactory.newConnection())
            .consume(packageId)
    }
}

private fun configureRabbitMq(config: LSportConfig): ConnectionFactory {
    return ConnectionFactory().apply {
        port = config.port
        username = config.username
        password = config.password
        virtualHost = config.virtualHost
        requestedHeartbeat = config.requestHeartbeat
        networkRecoveryInterval = config.networkRecoveryInterval
        isAutomaticRecoveryEnabled = config.isAutomaticRecoveryEnabled
    }
}
