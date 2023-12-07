package com.turbomates.kotlin.lsports.sdk.api.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubscribedFixturesRequest(
    @SerialName("SportIds")
    var sportIds: List<Long>? = null,
    @SerialName("LocationIds")
    var locationIds: List<Long>? = null,
    @SerialName("LeagueIds")
    var leagueIds: List<Long>? = null
) : Request()
