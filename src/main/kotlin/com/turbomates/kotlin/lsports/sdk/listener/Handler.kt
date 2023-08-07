package com.turbomates.kotlin.lsports.sdk.listener

import com.turbomates.kotlin.lsports.sdk.listener.message.FixtureUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.HeartbeatMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.KeepAliveMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.LivescoreUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.MarketUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.SettlementsMessage

interface Handler {
    suspend fun handle(message: FixtureUpdateMessage)
    suspend fun handle(message: LivescoreUpdateMessage)
    suspend fun handle(message: MarketUpdateMessage)
    suspend fun handle(message: KeepAliveMessage)
    suspend fun handle(message: HeartbeatMessage)
    suspend fun handle(message: SettlementsMessage)
}
