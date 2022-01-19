package com.turbomates.kotlin.lsports.sdk.api.inplay.request

data class CancelOrder(
    val fixtureIds: List<Int>? = null,
    val sportIds: List<Int>? = null,
    val providerIds: List<Int>? = null
)
