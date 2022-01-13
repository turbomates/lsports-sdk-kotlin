package com.turbomates.kotlin.lsports.sdk.model.message

import com.turbomates.kotlin.lsports.sdk.model.body.MarketUpdateBody
import com.turbomates.kotlin.lsports.sdk.model.header.MarketUpdateHeader

data class MarketUpdateMessage(
    override val header: MarketUpdateHeader,
    val body: MarketUpdateBody
) : Message
