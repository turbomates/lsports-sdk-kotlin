package com.turbomates.kotlin.lsports.sdk.listener.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeartbeatMessage(
    @SerialName("Header")
    override val header: Message.Header
) : Message
