package com.turbomates.kotlin.lsports.sdk.listener

import com.turbomates.kotlin.lsports.sdk.LSportsConfig

class InPlayListener(private val config: LSportsConfig) : Listener(config) {
    override suspend fun listen(handler: Handler, prefetchSize: Int) {
        connectionFactory.host = config.inPlayHost
        connectionFactory.virtualHost = config.inPlayVirtualHost
        consumer = Consumer(handler, connectionFactory.newConnection(), "_${config.inPlayPackageId}_", prefetchSize)
        consumer.consume()
    }
}
