package com.turbomates.kotlin.lsports.sdk.model.response

import com.turbomates.kotlin.lsports.sdk.model.Competition
import com.turbomates.kotlin.lsports.sdk.model.Event
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.OutrightFixture
import com.turbomates.kotlin.lsports.sdk.model.Response
import com.turbomates.kotlin.lsports.sdk.serializer.TimestampSerializer
import java.time.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OutrightFixturesResponse(
    @SerialName("Header")
    override val header: HeaderImpl,
    @SerialName("Body")
    val body: List<OuterCompetitionImpl>
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
    data class OuterCompetitionImpl(
        @SerialName("Id")
        override val id: Long,
        @SerialName("Name")
        override val name: String,
        @SerialName("Type")
        override val type: Competition.Type,
        @SerialName("Competitions")
        override val competitions: List<InnerCompetitionImpl>,
        @SerialName("Events")
        override val events: List<EventImpl>? = null
    ) : Competition

    @Serializable
    data class InnerCompetitionImpl(
        @SerialName("Id")
        override val id: Long,
        @SerialName("Name")
        override val name: String,
        @SerialName("Type")
        override val type: Competition.Type,
        @SerialName("Competitions")
        override val competitions: List<InnerCompetitionImpl>? = null,
        @SerialName("Events")
        override val events: List<EventImpl>
    ) : Competition

    @Serializable
    data class EventImpl(
        @SerialName("FixtureId")
        override val fixtureId: Long,
        @SerialName("OutrightFixture")
        val outrightFixture: OutrightFixture
    ) : Event
}
