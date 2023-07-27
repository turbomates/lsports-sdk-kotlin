package com.turbomates.kotlin.lsports.sdk.api.request

import com.turbomates.kotlin.lsports.sdk.serializer.EnumWithValueSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

@Serializable(with = SubscriptionStatusSerializer::class)
enum class SubscriptionStatus(val value: Int) {
    NOT_SUBSCRIBED(1),
    SUBSCRIBED(2)
}

private class SubscriptionStatusSerializer : EnumWithValueSerializer<SubscriptionStatus, Int>(
    "LeagueRequestSubscriptionStatus",
    Int.serializer(),
    { value },
    { v -> SubscriptionStatus.values().first { it.value == v } }
)
