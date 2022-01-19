package com.turbomates.kotlin.lsports.sdk.api.prematch.request

data class FixtureMarkets(
    val timestamp: Long? = null,
    val fromDate: Long? = null,
    val toDate: Long? = null,
    val sportIds: List<Int>? = null,
    val locationIds: List<Int>? = null,
    val leagueIds: List<Int>? = null,
    val fixtureIds: List<Int>? = null,
    val statuses: List<Int>? = null,
    val bookmakerIds: List<Int>? = null,
    val marketIds: List<Int>? = null
)
