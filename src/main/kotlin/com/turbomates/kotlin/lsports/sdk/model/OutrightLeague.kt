package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.datetime.LocalDateTime

data class OutrightLeague(
    val sport: Sport,
    val location: Location,
    val lastUpdate: LocalDateTime,
    val status: Scoreboard.Status
)
