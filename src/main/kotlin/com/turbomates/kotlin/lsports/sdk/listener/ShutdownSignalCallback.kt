package com.turbomates.kotlin.lsports.sdk.listener

import com.rabbitmq.client.Channel
import com.rabbitmq.client.ConsumerShutdownSignalCallback
import com.rabbitmq.client.ShutdownSignalException
import kotlin.system.exitProcess

internal class ShutdownSignalCallback(
    private val channel: Channel,
    private val block: (
        consumerTag: String?,
        sig: ShutdownSignalException,
        channel: Channel
    ) -> Unit = { _, _, _ ->
        channel.connection.close()
        channel.close()
        exitProcess(-1)
    }
) : ConsumerShutdownSignalCallback {
    override fun handleShutdownSignal(consumerTag: String?, sig: ShutdownSignalException) {
        block(consumerTag, sig, channel)
    }
}
