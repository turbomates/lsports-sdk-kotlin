package com.turbomates.kotlin.lsports.sdk.model.body

import com.turbomates.kotlin.lsports.sdk.model.event.LivescoreUpdateEvent

data class LivescoreUpdateBody(
    val events: List<LivescoreUpdateEvent>
) : Body
