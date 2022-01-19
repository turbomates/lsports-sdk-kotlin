package com.turbomates.kotlin.lsports.sdk.model

data class Livescore(
    val scoreboard: Scoreboard,
    val periods: List<Period>,
    val statistics: List<Statistic>,
    val liveScoreExtraData: ExtraData? = null
)
