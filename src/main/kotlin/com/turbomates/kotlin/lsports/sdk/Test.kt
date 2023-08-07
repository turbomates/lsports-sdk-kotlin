package com.turbomates.kotlin.lsports.sdk

import com.turbomates.kotlin.lsports.sdk.listener.Handler
import com.turbomates.kotlin.lsports.sdk.listener.message.FixtureUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.HeartbeatMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.KeepAliveMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.LivescoreUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.MarketUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.SettlementsMessage
import kotlinx.coroutines.delay
import java.time.OffsetDateTime
import java.time.ZoneOffset

suspend fun main() {
    val client = LSportsClient {
        username = "bugrahan.cilsal@gmail.com"
        password = "Ha2325jfs!"
        inPlayPackageId = "1607"
        preMatchPackageId = "1608"
    }
    // val listener = TestHandler()
    val nowUTC = OffsetDateTime.now(ZoneOffset.UTC)
    // if (!client.api.distributionStatus().body.isDistributionOn) {
    //     client.api.distributionStart()
    // }

    if (!client.preMatch.api.distributionStatus().body.isDistributionOn) {
        println("Restart distribution")
        client.preMatch.api.distributionStart()
        delay(5000)
    }
    client.preMatch.listener.listen(TestHandler(), 1)

    // val resp1 = client.preMatch.api.locations()
    // println(resp1)
    // val resp2 = client.preMatch.api.snapshot(PreMatchAPI.SnapshotAction.GET_FIXTURES) {
    //         fromDate = nowUTC.minusMinutes(10)
    //         toDate = nowUTC
    //         sportIds = listOf(6046)
    // }
        // locationIds = listOf(122)
        // sportIds = listOf(6046)
        // leagueIds = listOf(6)
        // isSettleable = true

    // println(resp2.body)

    // val status = client.preMatch.api.distributionStart()
    // println(status)

    // client.preMatch.listener.listen(10)

    // val resp1 = client.preMatch.api(PreMatchAPI.SnapshotAction.GET_EVENTS) {
    //     fromDate = nowUTC.minusMinutes(10)
    //     toDate = nowUTC
    //     sportIds = listOf(6046)
    // }
    // println(resp1)
}
//
class TestHandler : Handler {
    override suspend fun handle(message: KeepAliveMessage) {
        println("ok")
    }

    override suspend fun handle(message: FixtureUpdateMessage) {
        println("ok")
    }

    override suspend fun handle(message: HeartbeatMessage) {
        println("ok")
    }

    override suspend fun handle(message: LivescoreUpdateMessage) {
        println("ok")
    }

    override suspend fun handle(message: MarketUpdateMessage) {
        println("ok")
    }

    override suspend fun handle(message: SettlementsMessage) {
        println("ok")
    }
}
