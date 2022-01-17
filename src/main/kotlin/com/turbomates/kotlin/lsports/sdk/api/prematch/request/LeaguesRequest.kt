package com.turbomates.kotlin.lsports.sdk.api.prematch.request

data class LeaguesRequest(
    val guid: String,
    val sportIds: List<Int>? = null,
    val locationIds: List<Int>? = null
)
