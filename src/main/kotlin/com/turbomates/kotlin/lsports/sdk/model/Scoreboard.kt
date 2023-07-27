package com.turbomates.kotlin.lsports.sdk.model

import com.turbomates.kotlin.lsports.sdk.serializer.EnumWithValueSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

@Serializable
data class Scoreboard(
    @SerialName("Status")
    val status: Fixture.Status,
    @SerialName("Description")
    val description: Description? = null,
    @SerialName("CurrentPeriod")
    val currentPeriod: Int,
    @SerialName("Time")
    val timeInSec: Int,
    @SerialName("Results")
    val results: List<Result>? = null
) {
    @Serializable(with = DescriptionSerializer::class)
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

    private class DescriptionSerializer : EnumWithValueSerializer<Description, Int>(
        "ScoreboardDescription",
        Int.serializer(),
        { value },
        { v -> Description.values().first { it.value == v } }
    )
}
