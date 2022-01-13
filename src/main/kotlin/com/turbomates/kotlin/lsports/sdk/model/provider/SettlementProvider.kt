package com.turbomates.kotlin.lsports.sdk.model.provider

import com.turbomates.kotlin.lsports.sdk.model.bet.SettlementBet
import java.time.LocalDateTime

data class SettlementProvider(
    override val id: Long,
    override val name: String,
    override val lastUpdate: LocalDateTime,
    override val bets: List<SettlementBet>,
    override val providerFixtureId: String? = null,
    override val providerLeagueId: String? = null,
    override val providerMarketId: String? = null
) : Provider
