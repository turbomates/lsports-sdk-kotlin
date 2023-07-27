package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Participant(
    @SerialName("Id")
    val id: Long,
    @SerialName("Name")
    val name: String,
    @SerialName("Position")
    val position: Int,
    @SerialName("IsActive")
    val isActive: Boolean? = null,
    @SerialName("ParticipantExtraData")
    val extraData: List<ExtraData>? = null
)
