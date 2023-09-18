package com.turbomates.kotlin.lsports.sdk

import com.turbomates.kotlin.lsports.sdk.api.PreMatchAPI
import com.turbomates.kotlin.lsports.sdk.listener.Handler
import com.turbomates.kotlin.lsports.sdk.listener.message.FixtureUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.HeartbeatMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.KeepAliveMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.LivescoreUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.MarketUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.SettlementsMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.OffsetDateTime
import java.util.UUID
import kotlin.system.exitProcess

suspend fun main() {
    val jobs = Test().start()
    jobs.joinAll()
    println(jobs)
}

object Storage {
    var comeIds = mutableSetOf<UUID>()
    var processIds = mutableSetOf<UUID>()

    fun checkId(id: UUID) {
        if (!comeIds.contains(id)) throw Exception("aasd")
    }
}

class Test : CoroutineScope by CoroutineScope(Dispatchers.IO) {
    suspend fun start(): List<Job> {
        val client = LSportsClient {
            username = "bugrahan.cilsal@gmail.com"
            password = "Ha2325jfs!"
            inPlayPackageId = "1607"
            preMatchPackageId = "1608"
            apiRequestTimeoutMillis = 500000L
        }


       val job1 = launch {
                client.preMatch.listener.listen(TestHandler(), 10)
                println("End job")
            }


        return listOf(job1)
    }
}

class TestHandler : Handler {
    override suspend fun handle(message: FixtureUpdateMessage) {
    }

    override suspend fun handle(message: HeartbeatMessage) {
    }

    override suspend fun handle(message: LivescoreUpdateMessage) {
    }

    override suspend fun handle(message: KeepAliveMessage) {
    }

    override suspend fun handle(message: MarketUpdateMessage) {
        delay(150L)
    }

    override suspend fun handle(message: SettlementsMessage) {
    }
}
