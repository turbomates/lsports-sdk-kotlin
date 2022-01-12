package com.turbomates.kotlin.lsports.sdk.model

import java.time.LocalDateTime

data class OutrightFixture(
    val id: Int,
    val sport: Sport,
    val location: Location,
    val lastUpdateDateTime: LocalDateTime,
    val startDateTime: LocalDateTime,
    val status: Fixture.Status,
    val participants: List<Participant>,
    val externalProviderId: Int?,
    val extraData: ExtraData?
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
}
