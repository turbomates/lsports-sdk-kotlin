package com.turbomates.kotlin.lsports.sdk.listener

import com.turbomates.kotlin.lsports.sdk.LSportsConfig

class InPlayListener(
    private val config: LSportsConfig
) : Listener(config) {
    fun listen(handler: Handler, packageId: String) {
        connectionFactory.host = config.inPlayHost
        Consumer(handler, connectionFactory.newConnection())
            .consume(packageId)
    }
}
