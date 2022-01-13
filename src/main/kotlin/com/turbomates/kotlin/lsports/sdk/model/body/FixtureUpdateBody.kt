package com.turbomates.kotlin.lsports.sdk.model.body

import com.turbomates.kotlin.lsports.sdk.model.event.FixtureUpdateEvent

data class FixtureUpdateBody(
    val events: List<FixtureUpdateEvent>
) : Body
