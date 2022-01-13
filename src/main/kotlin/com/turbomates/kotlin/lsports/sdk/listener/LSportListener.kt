package com.turbomates.kotlin.lsports.sdk.listener

import com.rabbitmq.client.ConnectionFactory

class LSportListener(
    configuration: LSportClientConfig.() -> Unit
) {
    private val config = LSportClientConfig().apply(configuration)
    private val connectionFactory = configureRabbitMq(config)

    fun <Message>listenLive(handler: Handler<Message>, packageId: PackageId) {
        connectionFactory.host = config.liveHost
        Consumer(handler, connectionFactory.newConnection())
            .consume(packageId)
    }

    fun <Message>listenPreLive(handler: Handler<Message>, packageId: PackageId) {
        connectionFactory.host = config.preLiveHost
        Consumer(handler, connectionFactory.newConnection())
            .consume(packageId)
    }
}

private fun configureRabbitMq(config: LSportClientConfig): ConnectionFactory {
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
