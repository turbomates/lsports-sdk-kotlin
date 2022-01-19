package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.datetime.LocalDateTime

data class OutrightFixture(
    val sport: Sport,
    val location: Location,
    val lastUpdate: LocalDateTime,
    val startDate: LocalDateTime,
    val status: Scoreboard.Status,
    val participants: List<ParticipantImpl>,
    val fixtureExtraData: ExtraData? = null
) {
    data class ParticipantImpl(
        override val id: Long,
        override val name: String,
        override val rotationId: Long? = null,
        override val position: Int? = null,
        override val isActive: Boolean? = null,
        override val participantExtraData: ExtraData? = null
    ) : Participant
}
