package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OutrightLeague(
    @SerialName("Sport")
    val sport: Sport,
    @SerialName("Location")
    val location: Location,
    @SerialName("LastUpdate")
    val lastUpdate: LocalDateTime,
    @SerialName("Status")
    val status: Scoreboard.Status
)
