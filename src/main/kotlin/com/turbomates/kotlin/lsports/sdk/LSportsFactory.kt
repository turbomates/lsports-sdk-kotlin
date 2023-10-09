package com.turbomates.kotlin.lsports.sdk

import com.rabbitmq.client.ConnectionFactory
import com.turbomates.kotlin.lsports.sdk.api.InPlayAPI
import com.turbomates.kotlin.lsports.sdk.api.PreMatchAPI
import com.turbomates.kotlin.lsports.sdk.listener.Handler
import com.turbomates.kotlin.lsports.sdk.listener.Listener
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class LSportsFactory(private val config: LSportsConfig) {
    fun inPlayListener(
        handler: Handler,
        prefetchSize: Int = 20,
        coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default
    ): Listener {
        val connection = rabbitConnectionFactory().apply {
            host = config.inPlayHost
            virtualHost = config.inPlayVirtualHost
        }.newConnection()
        return Listener(
            connection,
            handler,
            "_${config.inPlayPackageId}_",
            prefetchSize,
            "InLive",
            coroutineDispatcher
        )
    }

    fun preMatchListener(
        handler: Handler,
        prefetchSize: Int = 20,
        coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default
    ): Listener {
        val connection = rabbitConnectionFactory().apply {
            host = config.preMatchHost
            virtualHost = config.preMatchVirtualHost
        }.newConnection()
        return Listener(
            connection,
            handler,
            "_${config.preMatchPackageId}_",
            prefetchSize,
            "PreMatch",
            coroutineDispatcher
        )
    }

    fun inPlayAPI(): InPlayAPI {
        return InPlayAPI(config)
    }

    fun preMatchAPI(): PreMatchAPI {
        return PreMatchAPI(config)
    }

    private fun rabbitConnectionFactory(): ConnectionFactory {
        return ConnectionFactory().apply {
            port = config.port
            username = config.username
            password = config.password
            requestedHeartbeat = config.requestHeartbeat
            networkRecoveryInterval = config.networkRecoveryInterval
            isAutomaticRecoveryEnabled = config.isAutomaticRecoveryEnabled
        }
    }
}
