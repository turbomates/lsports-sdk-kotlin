package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Bookmaker(
    @SerialName("Id")
    val id: Long,
    @SerialName("Name")
    val name: String
)
