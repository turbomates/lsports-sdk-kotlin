package com.turbomates.kotlin.lsports.sdk.model.provider

import com.turbomates.kotlin.lsports.sdk.model.bet.MarketUpdateBet
import java.time.LocalDateTime

data class MarketUpdateProvider(
    override val id: Long,
    override val name: String,
    override val lastUpdate: LocalDateTime,
    override val bets: List<MarketUpdateBet>,
    override val providerFixtureId: String? = null,
    override val providerLeagueId: String? = null,
    override val providerMarketId: String? = null
) : Provider
