package com.turbomates.kotlin.lsports.sdk.listener

import com.turbomates.kotlin.lsports.sdk.LSportsConfig

class PreMatchListener(
    private val config: LSportsConfig
) : Listener(config) {
    fun listen(handler: Handler) {
        connectionFactory.host = config.preMatchHost
        Consumer(handler, connectionFactory.newConnection())
            .consume(config.preMatchPackageId)
    }
}
