package com.turbomates.kotlin.lsports.sdk.model

import com.turbomates.kotlin.lsports.sdk.serializer.EnumWithValueSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

@Serializable(with = BetSuspensionReasonSerializer::class)
enum class BetSuspensionReason(val value: Int) {
    PROVIDERS(1),
    MAJORITY(3),
    THRESHOLD(4),
    MANDATORY_PROVIDERS(5),
    LIVESCORE_INCONSISTENCY(6),
    OUT_OF_PRICE_RANGE(7),
    LINE_TYPE_REMOVAL(8),
    OUT_OF_LINE_RANGE(9),
    FIXTURE_STATUS(10),
    MAIN_LINE_ONLY(11),
    DATA_LIMIT(12),
    OUTLIER(13),
    SETTINGS_CHANGE(14),
    FAIR_PRICE_CALCULATION(15),
    NEGATIVE_MARGIN(16)
}

private class BetSuspensionReasonSerializer : EnumWithValueSerializer<BetSuspensionReason, Int>(
    "BetSuspensionReason",
    Int.serializer(),
    { value },
    { v -> BetSuspensionReason.values().first { it.value == v } }
)
