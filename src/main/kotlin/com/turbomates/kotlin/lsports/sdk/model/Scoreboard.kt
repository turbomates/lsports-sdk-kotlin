package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Scoreboard(
    @SerialName("Status")
    val status: Status,
    @SerialName("Description")
    val description: Description? = null,
    @SerialName("CurrentPeriod")
    val currentPeriod: Period.Type,
    @SerialName("Time")
    val time: Long,
    @SerialName("Results")
    val results: List<Result>? = null
) {
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

    @Serializable
    enum class Description(val value: Int) {
        @SerialName("1")
        HALFTIME(1),
        @SerialName("2")
        OVERTIME_HALFTIME(2),
        @SerialName("3")
        HOME_RETIRED(3),
        @SerialName("4")
        AWAY_RETIRED(4),
        @SerialName("5")
        COVERAGE_LOST(5),
        @SerialName("6")
        MEDICAL_TIMEOUT(6),
        @SerialName("7")
        HOME_TIMEOUT(7),
        @SerialName("8")
        AWAY_TIMEOUT(8),
        @SerialName("9")
        TIMEOUT(9),
        @SerialName("10")
        HOME_WALKOVER(10),
        @SerialName("11")
        AWAY_WALKOVER(11)
    }
}
