package com.turbomates.kotlin.lsports.sdk.listener

import com.turbomates.kotlin.lsports.sdk.LSportsConfig

class PreMatchListener(private val config: LSportsConfig) : Listener(config) {

    override suspend fun listen(handler: Handler, prefetchSize: Int) {
        connectionFactory.host = config.preMatchHost
        consumer = Consumer(handler, connectionFactory.newConnection(), "_${config.preMatchPackageId}_", prefetchSize)
        consumer.consume()
    }
}
