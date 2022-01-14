package com.turbomates.kotlin.lsports.sdk.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Market(
    @SerialName("Id")
    val id: Int,
    @SerialName("Name")
    val name: String
)
