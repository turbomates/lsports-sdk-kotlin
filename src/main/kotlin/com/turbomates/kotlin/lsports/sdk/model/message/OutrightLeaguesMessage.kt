package com.turbomates.kotlin.lsports.sdk.model.message

import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Competition
import com.turbomates.kotlin.lsports.sdk.model.Event
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.Livescore
import com.turbomates.kotlin.lsports.sdk.model.Market
import com.turbomates.kotlin.lsports.sdk.model.OutrightLeague
import com.turbomates.kotlin.lsports.sdk.serializer.TimestampSerializer
import com.turbomates.kotlin.lsports.sdk.serializer.UUIDSerializer
import java.util.UUID
import java.time.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OutrightLeaguesMessage(
    @SerialName("Header")
    override val header: HeaderImpl,
    @SerialName("Body")
    val body: BodyImpl
) : Message {
    @Serializable
    data class BodyImpl(
        @SerialName("Competition")
        val competition: CompetitionImpl
    )

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
    data class CompetitionImpl(
        @SerialName("Id")
        override val id: Long,
        @SerialName("Name")
        override val name: String? = null,
        @SerialName("Type")
        override val type: Competition.Type,
        @SerialName("Competitions")
        override val competitions: List<InnerCompetitionImpl>? = null,
        @SerialName("Events")
        override val events: List<Event>? = null
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
        override val competitions: List<Competition>? = null,
        @SerialName("Events")
        override val events: List<EventImpl>? = null
    ) : Competition

    @Serializable
    data class EventImpl(
        @SerialName("FixtureId")
        override val fixtureId: Long,
        @SerialName("Livescore")
        val livescore: Livescore? = null,
        @SerialName("Markets")
        val markets: List<Market>? = null,
        @SerialName("OutrightLeague")
        val outrightLeague: OutrightLeague
    ) : Event
}
