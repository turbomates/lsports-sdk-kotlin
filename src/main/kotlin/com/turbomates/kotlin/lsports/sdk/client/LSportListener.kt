package com.turbomates.kotlin.lsports.sdk.client

import com.rabbitmq.client.ConnectionFactory

class LSportListener<Message>(
    private val handler: Handler<Message>,
    configuration: LSportClientConfig.() -> Unit
) {
    private val config = LSportClientConfig().apply(configuration)
    private val connectionFactory = configureRabbitMq(config)

    fun listenLive(queueName: String) {
        connectionFactory.host = config.liveHost
        Consumer(handler, connectionFactory.newConnection())
            .consume(queueName)
    }

    fun listenPreLive(queueName: String) {
        connectionFactory.host = config.preLiveHost
        Consumer(handler, connectionFactory.newConnection())
            .consume(queueName)
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
