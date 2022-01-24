package com.turbomates.kotlin.lsports.sdk.api.inplay.request

data class ScheduleRequest(
    var sportIds: List<String>? = null,
    var providerIds: List<String>? = null
)
