package com.turbomates.kotlin.lsports.sdk.model

data class Scoreboard(
    val status: Status,
    val description: Description? = null,
    val currentPeriod: Period.Type,
    val time: Long,
    val results: List<Result>
) {
    enum class Status(val value: Int) {
        NOT_STARTED(1),
        IN_PROGRESS(2),
        FINISHED(3),
        CANCELLED(4),
        POSTPONED(5),
        INTERRUPTED(6),
        ABANDONED(7),
        COVERAGE_LOST(8),
        ABOUT_TO_START(9)
    }

    enum class Description(val value: Int) {
        HALFTIME(1),
        OVERTIME_HALFTIME(2),
        HOME_RETIRED(3),
        AWAY_RETIRED(4),
        COVERAGE_LOST(5),
        MEDICAL_TIMEOUT(6),
        HOME_TIMEOUT(7),
        AWAY_TIMEOUT(8),
        TIMEOUT(9),
        HOME_WALKOVER(10),
        AWAY_WALKOVER(11)
    }
}
