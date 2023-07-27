package com.turbomates.kotlin.lsports.sdk.listener

import com.turbomates.kotlin.lsports.sdk._model.message.FixtureUpdateMessage
import com.turbomates.kotlin.lsports.sdk._model.message.HeartbeatMessage
import com.turbomates.kotlin.lsports.sdk._model.message.KeepAliveMessage
import com.turbomates.kotlin.lsports.sdk._model.message.LivescoreUpdateMessage
import com.turbomates.kotlin.lsports.sdk._model.message.MarketUpdateMessage
import com.turbomates.kotlin.lsports.sdk._model.message.OutrightLeaguesMessage
import com.turbomates.kotlin.lsports.sdk._model.message.SettlementMessage

interface Handler {
    suspend fun handle(message: FixtureUpdateMessage)
    suspend fun handle(message: LivescoreUpdateMessage)
    suspend fun handle(message: MarketUpdateMessage)
    suspend fun handle(message: KeepAliveMessage)
    suspend fun handle(message: HeartbeatMessage)
    suspend fun handle(message: SettlementMessage)
    suspend fun handle(message: OutrightLeaguesMessage)
}
