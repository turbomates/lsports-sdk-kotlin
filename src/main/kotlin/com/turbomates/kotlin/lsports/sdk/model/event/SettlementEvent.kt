package com.turbomates.kotlin.lsports.sdk.model.event

import com.turbomates.kotlin.lsports.sdk.model.market.MarketUpdateMarket

data class SettlementEvent(
    override val fixtureId: Long,
    val markets: List<MarketUpdateMarket>
) : Event
