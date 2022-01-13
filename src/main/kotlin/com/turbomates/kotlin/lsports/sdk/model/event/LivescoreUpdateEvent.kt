package com.turbomates.kotlin.lsports.sdk.model.event

import com.turbomates.kotlin.lsports.sdk.model.Livescore

data class LivescoreUpdateEvent(
    override val fixtureId: Long,
    val livescore: Livescore
) : Event
