package com.turbomates.kotlin.lsports.sdk.listener.message

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
        @SerialName("ActiveEvent")
        val activeEvent: List<Long>
    )
}
