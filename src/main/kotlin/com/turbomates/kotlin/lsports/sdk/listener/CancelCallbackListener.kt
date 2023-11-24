package com.turbomates.kotlin.lsports.sdk.listener

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.Channel
import kotlin.system.exitProcess

internal class CancelCallbackListener(
    private val channel: Channel,
    cancelBlock: ((
        consumerTag: String?,
        channel: Channel
    ) -> Unit)? = null
) : CancelCallback {
    private var block: (
        consumerTag: String?,
        channel: Channel
    ) -> Unit = { _, _ ->
        if (channel.connection.isOpen) {
            channel.connection.close()
        }
        if (channel.isOpen) {
            channel.close()
        }
        exitProcess(-1)
    }

    init {
        if (cancelBlock != null) {
            block = cancelBlock
        }
    }

    override fun handle(consumerTag: String?) {
        block(consumerTag, channel)
    }
}
