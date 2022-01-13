package com.turbomates.kotlin.lsports.sdk.model.bet

import java.time.LocalDateTime

data class SettlementBet(
    override val id: Long,
    override val name: String,
    override val line: String? = null,
    override val baseLine: String? = null,
    override val status: Bet.Status,
    override val startPrice: Double,
    override val price: Double,
    override val layPrice: Double? = null,
    override val priceVolume: Double? = null,
    override val layPriceVolume: Double? = null,
    override val providerBetId: String? = null,
    override val lastUpdate: LocalDateTime,
    val settlement: Bet.Settlement
) : Bet
