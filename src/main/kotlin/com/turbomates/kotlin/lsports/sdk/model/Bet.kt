package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    val participantId: Long?
    val lastUpdate: LocalDateTime

    @Serializable
    enum class Status(val value: Int) {
        @SerialName("1")
        OPEN(1),
        @SerialName("2")
        SUSPENDED(2),
        @SerialName("3")
        SETTLED(3)
    }

    @Serializable
    enum class Settlement(val value: Int) {
        @SerialName("-1")
        CANCELLED(-1),
        @SerialName("1")
        LOSER(1),
        @SerialName("2")
        WINNER(2),
        @SerialName("3")
        REFUND(3),
        @SerialName("4")
        HALF_LOST(4),
        @SerialName("5")
        HALF_WON(5)
    }
}
