package com.turbomates.kotlin.lsports.sdk.api.inplay.request

data class CancelFixtureOrdersRequest(
    var fixtureIds: List<String>? = null,
    var sportIds: List<String>? = null,
    var providerIds: List<String>? = null
)
