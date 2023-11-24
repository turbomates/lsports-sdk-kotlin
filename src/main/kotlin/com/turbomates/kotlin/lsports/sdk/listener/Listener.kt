package com.turbomates.kotlin.lsports.sdk.listener

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConsumerShutdownSignalCallback
import com.rabbitmq.client.ShutdownSignalException
import java.io.Closeable
import java.util.concurrent.ConcurrentHashMap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class Listener(
    private val connection: Connection,
    private val handler: Handler,
    private val queue: String,
    prefetchSize: Int,
    private val context: String,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : Closeable {
    private val channel = connection.createChannel().apply {
        basicQos(prefetchSize, false)
    }

    fun run(
        cancelBlock: ((consumerTag: String?, channel: Channel) -> Unit)? = null,
        shutdownBlock: ((consumerTag: String?, sig: ShutdownSignalException, channel: Channel) -> Unit)? = null
    ) {
        channel.basicConsume(
            queue, false,
            DeliverCallbackListener(ConcurrentHashMap(), handler, channel, context, dispatcher),
            CancelCallbackListener(channel, cancelBlock),
            ShutdownSignalCallback(channel, shutdownBlock)
        )
    }

    override fun close() {
        channel.close()
        connection.close()
    }
}

