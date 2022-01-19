package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface Competition {
    val id: Long
    val name: String
    val type: Type
    val competitions: List<Competition>?
    val events: List<Event>?

    @Serializable
    enum class Type(val value: Int) {
        @SerialName("1")
        TRACK_NAME(1),
        @SerialName("2")
        RACE_NAME(2),
        @SerialName("3")
        LEAGUE(3),
        @SerialName("4")
        SEASON(4)
    }
}
