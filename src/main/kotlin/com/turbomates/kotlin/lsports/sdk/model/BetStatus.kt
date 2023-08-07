package com.turbomates.kotlin.lsports.sdk.model

import com.turbomates.kotlin.lsports.sdk.serializer.EnumWithValueSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

@Serializable(with = BetStatusSerializer::class)
enum class BetStatus(val value: Int) {
    OPEN(1),
    SUSPENDED(2),
    SETTLED(3)
}

private class BetStatusSerializer : EnumWithValueSerializer<BetStatus, Int>(
    "BetStatus",
    Int.serializer(),
    { value },
    { v -> BetStatus.values().first { it.value == v } }
)
