package com.turbomates.kotlin.lsports.sdk.api.inplay.request

data class Schedule(
    val packageId: String,
    val sportIds: List<Int>? = null,
    val providerIds: List<Int>? = null
)
