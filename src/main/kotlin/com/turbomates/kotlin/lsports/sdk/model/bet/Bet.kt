package com.turbomates.kotlin.lsports.sdk.model.bet

import java.time.LocalDateTime

interface Bet {
    val id: Long
    val name: String
    val line: String?
    val baseLine: String?
    val status: Status
    val startPrice: Double
    val price: Double
    val layPrice: Double?
    val priceVolume: Double?
    val layPriceVolume: Double?
    val providerBetId: String?
    val lastUpdate: LocalDateTime

    enum class Status(val value: Int) {
        OPEN(1),
        SUSPENDED(2),
        SETTLED(3)
    }

    enum class Settlement(val value: Int) {
        CANCELLED(-1),
        LOSER(1),
        WINNER(2),
        REFUND(3),
        HALF_LOST(4),
        HALF_WON(5)
    }
}
