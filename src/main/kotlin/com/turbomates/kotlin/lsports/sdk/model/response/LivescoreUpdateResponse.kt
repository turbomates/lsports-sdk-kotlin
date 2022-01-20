package com.turbomates.kotlin.lsports.sdk.model.response

import com.turbomates.kotlin.lsports.sdk.model.Event
import com.turbomates.kotlin.lsports.sdk.model.Livescore
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.Response
import com.turbomates.kotlin.lsports.sdk.serializer.TimestampSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LivescoreUpdateResponse(
    @SerialName("Header")
    override val header: HeaderImpl,
    @SerialName("Body")
    val body: List<EventImpl>
) : Response {
    @Serializable
    data class HeaderImpl(
        @SerialName("Type")
        override val type: Message.Type,
        @SerialName("ServerTimestamp")
        @Serializable(with = TimestampSerializer::class)
        override val serverTimestamp: LocalDateTime
    ) : Header

    @Serializable
    data class EventImpl(
        @SerialName("FixtureId")
        override val fixtureId: Long,
        @SerialName("Livescore")
        val livescore: Livescore
    ) : Event
}
