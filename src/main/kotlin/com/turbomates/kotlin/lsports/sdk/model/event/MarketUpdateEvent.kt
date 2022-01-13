package com.turbomates.kotlin.lsports.sdk.model.event

import com.turbomates.kotlin.lsports.sdk.model.market.SettlementMarket

data class MarketUpdateEvent(
    override val fixtureId: Long,
    val markets: List<SettlementMarket>
) : Event
