package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.datetime.LocalDateTime

interface Provider {
    val id: Long
    val name: String
    val lastUpdate: LocalDateTime?
    val bets: List<Bet>?
    val providerFixtureId: String?
    val providerLeagueId: String?
    val providerMarketId: String?
}
