package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Market<T>(
    @SerialName("Id")
    val id: Long,
    @SerialName("Name")
    val name: String,
    @SerialName("MainLine")
    val mainLine: String? = null,
    @SerialName("Bets")
    val bets: List<T>
)
