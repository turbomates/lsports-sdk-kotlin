package com.turbomates.kotlin.lsports.sdk.model.provider

import com.turbomates.kotlin.lsports.sdk.model.bet.Bet
import java.time.LocalDateTime

interface Provider {
    val id: Long
    val name: String
    val lastUpdate: LocalDateTime
    val bets: List<Bet>
    val providerFixtureId: String?
    val providerLeagueId: String?
    val providerMarketId: String?
}
