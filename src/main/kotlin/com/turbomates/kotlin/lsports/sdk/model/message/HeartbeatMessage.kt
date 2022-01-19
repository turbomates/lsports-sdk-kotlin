package com.turbomates.kotlin.lsports.sdk.model.message

import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.serializer.TimestampSerializer
import com.turbomates.kotlin.lsports.sdk.serializer.UUIDSerializer
import java.util.UUID
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeartbeatMessage(
    @SerialName("Header")
    override val header: HeaderImpl
) : Message {
    @Serializable
    data class HeaderImpl(
        @SerialName("Type")
        override val type: Message.Type,
        @SerialName("ServerTimestamp")
        @Serializable(with = TimestampSerializer::class)
        override val serverTimestamp: LocalDateTime,
        @SerialName("MsgGuid")
        @Serializable(with = UUIDSerializer::class)
        val msgGuid: UUID
    ) : Header
}
