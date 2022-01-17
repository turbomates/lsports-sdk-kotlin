package com.turbomates.kotlin.lsports.sdk.api.prematch.request

data class OutrightLeaguesRequest(
    val guid: String,
    val timestamp: Long? = null,
    val sportIds: List<Int>? = null,
    val locationIds: List<Int>? = null,
    val fixtureIds: List<Int>? = null,
    val statuses: List<Int>? = null
)
