package com.turbomates.kotlin.lsports.sdk.model

interface Competition {
    val id: Long
    val name: String
    val type: Type
    val competitions: List<Competition>?
    val events: List<Event>?

    enum class Type(val value: Int) {
        TRACK_NAME(1),
        RACE_NAME(2),
        LEAGUE(3),
        SEASON(4)
    }
}
