package com.turbomates.kotlin.lsports.sdk.model

data class Competition(
    val id: Int,
    val name: String,
    val type: Type,
    val events: List<Event>,
    val competitions: List<Competition>?
) {
    enum class Type(val value: Int) {
        TRACK_NAME(1),
        RACE_NAME(2),
        LEAGUE(3),
        SEASON(4)
    }
}
