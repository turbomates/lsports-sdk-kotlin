package com.turbomates.kotlin.lsports.sdk.model

import com.turbomates.kotlin.lsports.sdk.serializer.LocalDateTimeSerializer
import java.time.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OutrightLeague(
    @SerialName("Sport")
    val sport: Sport,
    @SerialName("Location")
    val location: Location,
    @SerialName("LastUpdate")
    @Serializable(with = LocalDateTimeSerializer::class)
    val lastUpdate: LocalDateTime,
    @SerialName("Status")
    val status: Scoreboard.Status
)
