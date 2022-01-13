package com.turbomates.kotlin.lsports.sdk.model.body

import com.turbomates.kotlin.lsports.sdk.model.event.SettlementEvent

data class SettlementBody(
    val events: List<SettlementEvent>
) : Body
