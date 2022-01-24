package com.turbomates.kotlin.lsports.sdk.model

import java.time.LocalDateTime

interface Provider {
    val id: Long
    val name: String
    val lastUpdate: LocalDateTime?
    val bets: List<Bet>?
    val providerFixtureId: String?
    val providerLeagueId: String?
    val providerMarketId: String?
}
