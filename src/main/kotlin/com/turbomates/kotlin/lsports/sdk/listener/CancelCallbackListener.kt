package com.turbomates.kotlin.lsports.sdk.listener

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.Channel
import kotlin.system.exitProcess

internal class CancelCallbackListener(
    private val channel: Channel,
    private val block: (
        consumerTag: String?,
        channel: Channel
    ) -> Unit = { _, _ ->
        channel.connection.close()
        channel.close()
        exitProcess(-1)
    }
) : CancelCallback {
    override fun handle(consumerTag: String?) {
        block(consumerTag, channel)
    }
}
