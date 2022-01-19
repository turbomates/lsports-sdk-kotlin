package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KeepAlive(
    @SerialName("ActiveEvents")
    val activeEvents: List<Long>,
    @SerialName("ExtraData")
    val extraData: ExtraData? = null,
    @SerialName("ProviderId")
    val providerId: Int? = null
)
