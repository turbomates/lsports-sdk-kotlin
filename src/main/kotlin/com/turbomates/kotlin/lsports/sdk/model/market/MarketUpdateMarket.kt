package com.turbomates.kotlin.lsports.sdk.model.market

import com.turbomates.kotlin.lsports.sdk.model.provider.MarketUpdateProvider

data class MarketUpdateMarket(
    override val id: Long,
    override val name: String,
    override val providers: List<MarketUpdateProvider>,
) : Market
