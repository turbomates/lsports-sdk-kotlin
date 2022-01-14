package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Location(
    @SerialName("Id")
    val id: Int,
    @SerialName("Name")
    val name: String
)
