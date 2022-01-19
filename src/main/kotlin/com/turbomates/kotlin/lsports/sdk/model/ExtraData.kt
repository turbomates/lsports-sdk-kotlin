package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias ExtraData = List<Data>

@Serializable
data class Data(
    @SerialName("Name")
    val name: String,
    @SerialName("Value")
    val value: String
)
