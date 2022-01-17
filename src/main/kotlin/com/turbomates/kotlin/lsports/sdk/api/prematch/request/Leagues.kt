package com.turbomates.kotlin.lsports.sdk.api.prematch.request

data class Leagues(
    val guid: String,
    val sportIds: List<Int>? = null,
    val locationIds: List<Int>? = null
)
