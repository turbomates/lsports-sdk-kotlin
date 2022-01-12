package com.turbomates.kotlin.lsports.sdk.rabbit

import com.rabbitmq.client.ConnectionFactory
import com.turbomates.kotlin.lsports.sdk.infrastructre.config.Config
import com.turbomates.kotlin.lsports.sdk.infrastructre.config.Configuration

class ListenerWorker (private val listener: Listener) {
    private val config = Configuration.load()
    private val connectionFactory = configureRabbitMq(config)

    fun listenLive() {
        connectionFactory.setHost(isLive = true, config)
        Consumer(listener, connectionFactory.newConnection())
            .consume(config.rabbit.queue.live)
    }

    fun listenPreLive() {
        connectionFactory.setHost(isLive = false, config)
        Consumer(listener, connectionFactory.newConnection())
            .consume(config.rabbit.queue.preLive)
    }

    private fun configureRabbitMq(config: Config): ConnectionFactory {
        return ConnectionFactory().apply {
            port = config.rabbit.port
            username = config.rabbit.username
            password = config.rabbit.password
            virtualHost = config.rabbit.virtualHost
            requestedHeartbeat = config.rabbit.heartbeat
            networkRecoveryInterval = config.rabbit.networkRecovery
            isAutomaticRecoveryEnabled = config.rabbit.autoRecovery
        }
    }

    private fun ConnectionFactory.setHost(isLive: Boolean, config: Config): ConnectionFactory {
        return apply {
            host = if (isLive) config.rabbit.host.live
                else config.rabbit.host.preLive
        }
    }
}
