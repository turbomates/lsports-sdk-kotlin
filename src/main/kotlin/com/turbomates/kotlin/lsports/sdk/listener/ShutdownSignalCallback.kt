package com.turbomates.kotlin.lsports.sdk.listener

import com.rabbitmq.client.Channel
import com.rabbitmq.client.ConsumerShutdownSignalCallback
import com.rabbitmq.client.ShutdownSignalException
import kotlin.system.exitProcess

internal class ShutdownSignalCallback(
    private val channel: Channel,
    shutdownBlock: ((
        consumerTag: String?,
        sig: ShutdownSignalException,
        channel: Channel
    ) -> Unit)? = null
) : ConsumerShutdownSignalCallback {
    private var block: (
        consumerTag: String?,
        sig: ShutdownSignalException,
        channel: Channel
    ) -> Unit = { _, _,_ ->
        if (channel.connection.isOpen) {
            channel.connection.close()
        }
        if (channel.isOpen) {
            channel.close()
        }
        exitProcess(-1)
    }

    init {
        if (shutdownBlock != null) {
            block = shutdownBlock
        }
    }

    override fun handleShutdownSignal(consumerTag: String?, sig: ShutdownSignalException) {
        block(consumerTag, sig, channel)
    }
}
