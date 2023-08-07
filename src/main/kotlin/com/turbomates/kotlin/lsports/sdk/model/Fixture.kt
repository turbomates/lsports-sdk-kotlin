@file:UseSerializers(OffsetDateTimeSerializer::class)

package com.turbomates.kotlin.lsports.sdk.model

import com.turbomates.kotlin.lsports.sdk.serializer.EnumWithValueSerializer
import com.turbomates.kotlin.lsports.sdk.serializer.OffsetDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.builtins.serializer
import java.time.OffsetDateTime

@Serializable
data class Fixture(
    @SerialName("Sport")
    val sport: Sport,
    @SerialName("Location")
    val location: Location,
    @SerialName("League")
    val league: League,
    @SerialName("LastUpdate")
    val lastUpdate: OffsetDateTime,
    @SerialName("StartDate")
    val startDate: OffsetDateTime,
    @SerialName("Status")
    val status: Status,
    @SerialName("Participants")
    val participants: List<Participant>,
    @SerialName("FixtureExtraData")
    val fixtureExtraData: List<ExtraData>? = null,
    @SerialName("ExternalProviderId")
    val externalProviderId: Long? = null,
    @SerialName("Subscription")
    val subscription: Subscription
) {
    @Serializable(with = StatusSerializer::class)
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

    private class StatusSerializer : EnumWithValueSerializer<Status, Int>(
        "FixtureStatus",
        Int.serializer(),
        { value },
        { v -> Status.values().first { it.value == v } }
    )
}
