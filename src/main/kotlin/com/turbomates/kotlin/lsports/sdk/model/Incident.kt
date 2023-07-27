package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Incident(
    @SerialName("Period")
    val period: Int,
    @SerialName("IncidentType")
    val incidentType: Statistic.Type,
    @SerialName("Seconds")
    val seconds: Long,
    @SerialName("ParticipantPosition")
    val participantPosition: Int,
    @SerialName("PlayerId")
    val playerId: Int? = null,
    @SerialName("Results")
    val results: List<Result>,
    @SerialName("PlayerName")
    val playerName: String? = null
)
