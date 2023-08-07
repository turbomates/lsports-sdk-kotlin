package com.turbomates.kotlin.lsports.sdk.model

import com.turbomates.kotlin.lsports.sdk.serializer.EnumWithValueSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

@Serializable(with = BetSettlementSerializer::class)
enum class BetSettlement(val value: Int) {
    CANCELLED(-1),
    LOSER(1),
    WINNER(2),
    REFUND(3),
    HALF_LOST(4),
    HALF_WON(5)
}

private class BetSettlementSerializer : EnumWithValueSerializer<BetSettlement, Int>(
    "BetSettlement",
    Int.serializer(),
    { value },
    { v -> BetSettlement.values().first { it.value == v } }
)
