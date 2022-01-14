package com.turbomates.kotlin.lsports.sdk.model

data class Event(
    val fixtureId: Int,
    val fixture: Fixture?,
    val markets: List<Any>,
    val livescore: Any
)
