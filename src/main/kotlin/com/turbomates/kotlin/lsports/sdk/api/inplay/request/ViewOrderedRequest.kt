package com.turbomates.kotlin.lsports.sdk.api.inplay.request

class ViewOrderedRequest(
    val packageId: String,
    val fixtureIds: List<Int>? = null,
    val providerIds: List<Int>? = null,
    val fromDate: Long? = null,
    val toDate: Long? = null
)
