package com.turbomates.kotlin.lsports.sdk.api.prematch.request

data class LeaguesRequest(
    var sportIds: List<String>? = null,
    var locationIds: List<String>? = null
)
