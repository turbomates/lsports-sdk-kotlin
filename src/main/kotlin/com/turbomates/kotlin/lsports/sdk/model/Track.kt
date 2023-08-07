package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Track(
    @SerialName("Id")
    val id: String,
    @SerialName("Name")
    val name: String
)
