package com.turbomates.kotlin.lsports.sdk.model

import java.time.LocalDateTime

data class OutrightLeague(
    val sport: Sport,
    val location: Location,
    val lastUpdate: LocalDateTime,
    val status: Scoreboard.Status
)
