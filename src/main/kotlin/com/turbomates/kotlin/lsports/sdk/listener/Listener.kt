package com.turbomates.kotlin.lsports.sdk.listener

import com.rabbitmq.client.ConnectionFactory
import com.turbomates.kotlin.lsports.sdk.LSportsConfig
import java.io.Closeable

abstract class Listener(private val config: LSportsConfig) : Closeable {
    lateinit var consumer: Consumer

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

    override fun close() {
        try {
            consumer.close()
        } catch (expected: Exception) {
            if (expected is UninitializedPropertyAccessException)
                throw UninitializedPropertyAccessException("Consumer has not been initialized")

            throw expected
        }
    }
}
