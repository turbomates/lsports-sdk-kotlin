package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExtraData(
    @SerialName("Name")
    val name: String,
    @SerialName("Value")
    val value: String
)
