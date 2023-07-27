package com.turbomates.kotlin.lsports.sdk.api.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleRequest(
    @SerialName("SportsId")
    var sportIds: List<Long>,
    @SerialName("LocationsId")
    var locationIds: List<Long>? = listOf(),
    @SerialName("LeaguesId")
    var leagueIds: List<Long>? = listOf()
) : Request()
