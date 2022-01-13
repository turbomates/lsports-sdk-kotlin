package com.turbomates.kotlin.lsports.sdk.model.market

import com.turbomates.kotlin.lsports.sdk.model.provider.SettlementProvider

data class SettlementMarket(
    override val id: Long,
    override val name: String,
    override val providers: List<SettlementProvider>,
) : Market
