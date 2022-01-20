package com.turbomates.kotlin.lsports.sdk.api

import com.turbomates.kotlin.lsports.sdk.LSportsConfig
import com.turbomates.kotlin.lsports.sdk.api.inplay.request.CancelOrder
import com.turbomates.kotlin.lsports.sdk.api.inplay.request.Order
import com.turbomates.kotlin.lsports.sdk.api.inplay.request.Schedule
import com.turbomates.kotlin.lsports.sdk.api.inplay.request.Snapshot
import com.turbomates.kotlin.lsports.sdk.api.inplay.request.ViewOrdered
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class InPlayAPI(
    private val config: LSportsConfig
) {
    private suspend inline fun <reified T> get(
        path: String,
        parameters: HttpRequestBuilder.() -> Unit = {}
    ): T {
        return API.client.get(config.inPlayUrl + path) {
            parameter("username", config.username)
            parameter("password", config.password)
            parameter("packageid", config.inPlayPackageId)
            parameters()
        }
    }

    suspend fun schedule(request: Schedule): Any {
        return get("/schedule/GetInPlaySchedule") {
            parameter("sportIds", request.sportIds.toString())
            parameter("providerIds", request.providerIds.toString())
        }
    }

    suspend fun orderFixtures(request: Order): Any {
        return get("/schedule/OrderFixtures") {
            parameter("sportIds", request.sportIds.toString())
            parameter("fixtureIds", request.fixtureIds.toString())
            parameter("providerIds", request.providerIds.toString())
        }
    }

    suspend fun cancelFixtureOrders(request: CancelOrder): Any {
        return get("/schedule/CancelFixtureOrders") {
            parameter("sportIds", request.sportIds.toString())
            parameter("fixtureIds", request.fixtureIds.toString())
            parameter("providerIds", request.providerIds.toString())
        }
    }

    suspend fun orderedFixtures(request: ViewOrdered): Any {
        return get("/schedule/GetOrderedFixtures") {
            parameter("fixtureIds", request.fixtureIds.toString())
            parameter("providerIds", request.providerIds.toString())
            parameter("fromDate", request.fromDate)
            parameter("toDate", request.toDate)
        }
    }

    suspend fun snapshot(request: Snapshot): Any {
        return get("/Snapshot/GetSnapshotJson") {
            parameter("fixtureIds", request.fixtureIds.toString())
        }
    }

    suspend fun enablePackage(): Unit = get("/Package/EnablePackage")
    suspend fun disablePackage(): Unit = get("/Package/DisablePackage")

    private fun List<Any>?.toString() = this?.joinToString(",")
}
