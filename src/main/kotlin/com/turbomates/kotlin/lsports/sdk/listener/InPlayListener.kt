package com.turbomates.kotlin.lsports.sdk.listener

import com.turbomates.kotlin.lsports.sdk.LSportsConfig

class InPlayListener(
    private val config: LSportsConfig
) : Listener(config) {

    override suspend fun listen(handler: Handler, prefetchSize: Int) {
        connectionFactory.host = config.inPlayHost
        Consumer(handler, connectionFactory.newConnection(), config.inPlayQueueName(), prefetchSize).consume()
    }
}
