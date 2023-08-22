package com.turbomates.kotlin.lsports.sdk.model

import com.turbomates.kotlin.lsports.sdk.serializer.EnumWithValueSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

interface Bet {
    val id: Long
    val name: String
    val status: Status
    val price: Double
    val startPrice: Double
    val layPrice: Double?
    val priceVolume: Double?
    val layPriceVolume: Double?
    val line: String?
    val baseLine: String?
    val providerBetId: String?

    @Serializable(with = StatusSerializer::class)
    enum class Status(val value: Int) {
        OPEN(1),
        SUSPENDED(2),
        SETTLED(3)
    }

    @Serializable(with = SettlementSerializer::class)
    enum class Settlement(val value: Int) {
        CANCELLED(-1),
        LOSER(1),
        WINNER(2),
        REFUND(3),
        HALF_LOST(4),
        HALF_WON(5)
    }

    private class StatusSerializer : EnumWithValueSerializer<Status, Int>(
        "BetStatus",
        Int.serializer(),
        { value },
        { v -> Status.values().first { it.value == v } }
    )

    private class SettlementSerializer : EnumWithValueSerializer<Settlement, Int>(
        "BetSettlement",
        Int.serializer(),
        { value },
        { v -> Settlement.values().first { it.value == v } }
    )
}
