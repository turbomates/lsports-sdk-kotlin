package com.turbomates.kotlin.lsports.sdk.api.inplay.request

data class OrderFixturesRequest(
    var fixtureIds: List<String>? = null,
    var sportIds: List<String>? = null,
    var providerIds: List<String>? = null
)
