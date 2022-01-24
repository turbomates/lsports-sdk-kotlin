package com.turbomates.kotlin.lsports.sdk.model

import com.turbomates.kotlin.lsports.sdk.serializer.LocalDateTimeSerializer
import java.time.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OutrightFixture(
    @SerialName("Sport")
    val sport: Sport,
    @SerialName("Location")
    val location: Location,
    @SerialName("LastUpdate")
    @Serializable(with = LocalDateTimeSerializer::class)
    val lastUpdate: LocalDateTime,
    @SerialName("StartDate")
    @Serializable(with = LocalDateTimeSerializer::class)
    val startDate: LocalDateTime,
    @SerialName("Status")
    val status: Scoreboard.Status,
    @SerialName("Participants")
    val participants: List<ParticipantImpl>,
    @SerialName("FixtureExtraData")
    val fixtureExtraData: ExtraData? = null
) {
    @Serializable
    data class ParticipantImpl(
        @SerialName("Id")
        override val id: Long,
        @SerialName("Name")
        override val name: String,
        @SerialName("RotationId")
        override val rotationId: Long? = null,
        @SerialName("Position")
        override val position: Int? = null,
        @SerialName("IsActive")
        override val isActive: Boolean? = null,
        @SerialName("ParticipantExtraData")
        override val participantExtraData: ExtraData? = null
    ) : Participant
}
