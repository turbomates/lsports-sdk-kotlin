package com.turbomates.kotlin.lsports.sdk.api.inplay.request

data class OrderedFixturesRequest(
    var fixtureIds: List<String>? = null,
    var providerIds: List<String>? = null,
    var fromDate: Long? = null,
    var toDate: Long? = null
)
