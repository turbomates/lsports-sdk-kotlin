package com.turbomates.kotlin.lsports.sdk.model.message

import com.turbomates.kotlin.lsports.sdk.model.ExtraData
import com.turbomates.kotlin.lsports.sdk.model.Location
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Participant
import com.turbomates.kotlin.lsports.sdk.model.Sport
import com.turbomates.kotlin.lsports.sdk.model.Body
import com.turbomates.kotlin.lsports.sdk.model.Event
import com.turbomates.kotlin.lsports.sdk.model.Fixture
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.League
import com.turbomates.kotlin.lsports.sdk.model.Livescore
import com.turbomates.kotlin.lsports.sdk.model.Market
import com.turbomates.kotlin.lsports.sdk.serializer.LocalDateTimeSerializer
import com.turbomates.kotlin.lsports.sdk.serializer.TimestampSerializer
import com.turbomates.kotlin.lsports.sdk.serializer.UUIDSerializer
import java.util.UUID
import java.time.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FixtureUpdateMessage(
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
        @SerialName("Fixture")
        val fixture: FixtureImpl,
        @SerialName("Livescore")
        val livescore: Livescore? = null,
        @SerialName("Markets")
        val markets: List<Market>? = null
    ) : Event

    @Serializable
    data class FixtureImpl(
        @SerialName("Sport")
        override val sport: Sport,
        @SerialName("Location")
        override val location: Location,
        @SerialName("League")
        override val league: LeagueImpl,
        @SerialName("LastUpdate")
        @Serializable(with = LocalDateTimeSerializer::class)
        override val lastUpdate: LocalDateTime,
        @SerialName("StartDate")
        @Serializable(with = LocalDateTimeSerializer::class)
        override val startDate: LocalDateTime,
        @SerialName("Status")
        override val status: Fixture.Status,
        @SerialName("Participants")
        override val participants: List<ParticipantImpl>,
        @SerialName("FixtureExtraData")
        override val fixtureExtraData: ExtraData? = null,
        @SerialName("ExternalProviderId")
        override val externalProviderId: Long? = null
    ) : Fixture

    @Serializable
    data class LeagueImpl(
        @SerialName("Id")
        override val id: Long,
        @SerialName("Name")
        override val name: String
    ) : League

    @Serializable
    data class ParticipantImpl(
        @SerialName("Id")
        override val id: Long,
        @SerialName("Name")
        override val name: String,
        @SerialName("Position")
        override val position: Int,
        @SerialName("RotationId")
        override val rotationId: Long? = null,
        @SerialName("IsActive")
        override val isActive: Boolean? = null,
        @SerialName("ParticipantExtraData")
        override val participantExtraData: ExtraData? = null
    ) : Participant
}
