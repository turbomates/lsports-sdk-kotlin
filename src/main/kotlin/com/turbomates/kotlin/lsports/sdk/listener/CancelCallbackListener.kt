package com.turbomates.kotlin.lsports.sdk.listener

import com.rabbitmq.client.CancelCallback

internal class CancelCallbackListener: CancelCallback {
    override fun handle(consumerTag: String?) {
        throw CancelCallbackListenerException("Listener was cancelled $consumerTag")
    }

    private class CancelCallbackListenerException(message: String) : Exception(message)
}
