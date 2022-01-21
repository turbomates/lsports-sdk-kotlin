package com.turbomates.kotlin.lsports.sdk.model.response

import com.turbomates.kotlin.lsports.sdk.model.Event
import com.turbomates.kotlin.lsports.sdk.model.ExtraData
import com.turbomates.kotlin.lsports.sdk.model.Fixture
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.League
import com.turbomates.kotlin.lsports.sdk.model.Location
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Participant
import com.turbomates.kotlin.lsports.sdk.model.Response
import com.turbomates.kotlin.lsports.sdk.model.Sport
import com.turbomates.kotlin.lsports.sdk.serializer.TimestampSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FixtureUpdateResponse(
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
        @SerialName("Fixture")
        val fixture: FixtureImpl
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
        override val lastUpdate: LocalDateTime,
        @SerialName("StartDate")
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
