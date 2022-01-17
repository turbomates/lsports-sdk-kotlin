package com.turbomates.kotlin.lsports.sdk.api.inplay.request

data class ScheduleRequest(
    val packageId: String,
    val sportIds: List<Int>? = null,
    val providerIds: List<Int>? = null
)
