package com.turbomates.kotlin.lsports.sdk.listener

import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.message.FixtureUpdateMessage
import com.turbomates.kotlin.lsports.sdk.model.message.HeartbeatMessage
import com.turbomates.kotlin.lsports.sdk.model.message.KeepAliveMessage
import com.turbomates.kotlin.lsports.sdk.model.message.LivescoreUpdateMessage
import com.turbomates.kotlin.lsports.sdk.model.message.MarketUpdateMessage
import com.turbomates.kotlin.lsports.sdk.model.message.SettlementMessage

interface Handler {
    fun handle(message: Message)
    fun handle(message: FixtureUpdateMessage)
    fun handle(message: LivescoreUpdateMessage)
    fun handle(message: MarketUpdateMessage)
    fun handle(message: KeepAliveMessage)
    fun handle(message: HeartbeatMessage)
    fun handle(message: SettlementMessage)
}
