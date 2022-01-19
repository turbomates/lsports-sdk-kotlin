package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface Fixture {
    val sport: Sport
    val location: Location
    val league: League
    val lastUpdate: LocalDateTime
    val startDate: LocalDateTime
    val status: Status
    val participants: List<Participant>
    val fixtureExtraData: ExtraData?
    val externalProviderId: Long?

    @Serializable
    enum class Status(val value: Int) {
        @SerialName("1")
        NOT_STARTED(1),
        @SerialName("2")
        IN_PROGRESS(2),
        @SerialName("3")
        FINISHED(3),
        @SerialName("4")
        CANCELLED(4),
        @SerialName("5")
        POSTPONED(5),
        @SerialName("6")
        INTERRUPTED(6),
        @SerialName("7")
        ABANDONED(7),
        @SerialName("8")
        COVERAGE_LOST(8),
        @SerialName("9")
        ABOUT_TO_START(9)
    }
}
