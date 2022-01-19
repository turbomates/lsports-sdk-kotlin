package com.turbomates.kotlin.lsports.sdk.model.message

import com.turbomates.kotlin.lsports.sdk.model.Body
import com.turbomates.kotlin.lsports.sdk.model.Event
import com.turbomates.kotlin.lsports.sdk.model.Livescore
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.Market
import com.turbomates.kotlin.lsports.sdk.serializer.TimestampSerializer
import com.turbomates.kotlin.lsports.sdk.serializer.UUIDSerializer
import kotlinx.datetime.LocalDateTime
import java.util.UUID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LivescoreUpdateMessage(
    @SerialName("Header")
    override val header: HeaderImpl,
    @SerialName("Body")
    val body: BodyImpl
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
        val msgGuid: UUID,
        @SerialName("MsgId")
        val msgId: Long
    ) : Header

    @Serializable
    data class BodyImpl(
        @SerialName("Events")
        val events: List<EventImpl>
    ) : Body

    @Serializable
    data class EventImpl(
        @SerialName("FixtureId")
        override val fixtureId: Long,
        @SerialName("Livescore")
        val livescore: Livescore,
        @SerialName("Markets")
        val markets: List<Market>? = null
    ) : Event
}
