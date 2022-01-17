package com.turbomates.kotlin.lsports.sdk

import com.rabbitmq.client.ConnectionFactory
import com.turbomates.kotlin.lsports.sdk.api.inplay.InPlayAPI
import com.turbomates.kotlin.lsports.sdk.api.prematch.PreMatchAPI
import com.turbomates.kotlin.lsports.sdk.listener.InPlayListener
import com.turbomates.kotlin.lsports.sdk.listener.PreMatchListener

class LSportsClient (
    configuration: LSportsConfig.() -> Unit
) {
    private val config = LSportsConfig().apply(configuration)
    private val connectionFactory = configureRabbitMq(config)

    val inPlay = InPlay(
        api = InPlayAPI(config),
        listener = InPlayListener(config, connectionFactory)
    )
    val preMatch = PreMatch(
        api = PreMatchAPI(config),
        listener = PreMatchListener(config, connectionFactory))

    class InPlay(
        val api: InPlayAPI,
        val listener: InPlayListener
    )

    class PreMatch(
        val api: PreMatchAPI,
        val listener: PreMatchListener
    )
}

private fun configureRabbitMq(config: LSportsConfig): ConnectionFactory {
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
