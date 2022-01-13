package com.turbomates.kotlin.lsports.sdk.model.body

import com.turbomates.kotlin.lsports.sdk.model.event.MarketUpdateEvent

data class MarketUpdateBody(
    val events: List<MarketUpdateEvent>
) : Body
