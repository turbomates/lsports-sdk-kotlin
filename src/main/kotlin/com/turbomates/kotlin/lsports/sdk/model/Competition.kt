package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Competition(
    @SerialName("Id")
    val id: Long,
    @SerialName("Name")
    val name: String,
    @SerialName("Type")
    val type: Int,
    @SerialName("TrackId")
    val trackId: Long
) {
    // TODO("Add enum type after")
    @Serializable
    enum class Type
}
