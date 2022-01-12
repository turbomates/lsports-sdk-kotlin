package com.turbomates.kotlin.lsports.sdk.client

import com.rabbitmq.client.ConnectionFactory

class LSportClient(
    private val listener: Listener,
    configuration: LSportClientConfig.() -> Unit
) {
    private val config = LSportClientConfig().apply(configuration)
    private val connectionFactory = configureRabbitMq(config)

    fun listenLive(queueName: String) {
        connectionFactory.host = config.liveHost
        Consumer(listener, connectionFactory.newConnection())
            .consume(queueName)
    }

    fun listenPreLive(queueName: String) {
        connectionFactory.host = config.preLiveHost
        Consumer(listener, connectionFactory.newConnection())
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
