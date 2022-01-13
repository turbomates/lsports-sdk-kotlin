package com.turbomates.kotlin.lsports.sdk.model.message

import com.turbomates.kotlin.lsports.sdk.model.body.SettlementBody
import com.turbomates.kotlin.lsports.sdk.model.header.SettlementHeader

data class SettlementMessage(
    override val header: SettlementHeader,
    val body: SettlementBody
) : Message
