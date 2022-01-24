package com.turbomates.kotlin.lsports.sdk.api.prematch.request

data class EventsRequest(
    var timestamp: Long? = null,
    var fromDate: Long? = null,
    var toDate: Long? = null,
    var sportIds: List<String>? = null,
    var locationIds: List<String>? = null,
    var leagueIds: List<String>? = null,
    var fixtureIds: List<String>? = null,
    var statuses: List<String>? = null,
    var bookmakerIds: List<String>? = null,
    var marketIds: List<String>? = null
)
