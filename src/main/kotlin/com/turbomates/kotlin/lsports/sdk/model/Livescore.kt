package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Livescore(
    @SerialName("Scoreboard")
    val scoreboard: Scoreboard,
    @SerialName("Periods")
    val periods: List<Period>? = null,
    @SerialName("Statistics")
    val statistics: List<Statistic>? = null,
    @SerialName("LivescoreExtraData")
    val livescoreExtraData: List<ExtraData>? = null
)
