package com.turbomates.kotlin.lsports.sdk.listener.message

import com.turbomates.kotlin.lsports.sdk.model.ExtraData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KeepAliveMessage(
    @SerialName("Header")
    override val header: Message.Header,
    @SerialName("Body")
    val body: Body
) : Message {
    @Serializable
    data class Body(
        @SerialName("KeepAlive")
        val keepAlive: KeepAlive
    )

    @Serializable
    data class KeepAlive(
        @SerialName("ActiveEvents")
        val activeEvents: List<Long>,
        @SerialName("ExtraData")
        val extraData: List<ExtraData>? = null
    )
}
