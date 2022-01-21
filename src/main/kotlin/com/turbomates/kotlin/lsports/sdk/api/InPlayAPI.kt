package com.turbomates.kotlin.lsports.sdk.api

import com.turbomates.kotlin.lsports.sdk.LSportsConfig
import com.turbomates.kotlin.lsports.sdk.api.inplay.request.CancelFixtureOrdersRequest
import com.turbomates.kotlin.lsports.sdk.api.inplay.request.OrderFixturesRequest
import com.turbomates.kotlin.lsports.sdk.api.inplay.request.OrderedFixturesRequest
import com.turbomates.kotlin.lsports.sdk.api.inplay.request.ScheduleRequest
import com.turbomates.kotlin.lsports.sdk.api.inplay.request.SnapshotRequest
import com.turbomates.kotlin.lsports.sdk.model.response.CancelFixtureOrdersResponse
import com.turbomates.kotlin.lsports.sdk.model.response.OrderFixturesResponse
import com.turbomates.kotlin.lsports.sdk.model.response.ScheduleResponse
import com.turbomates.kotlin.lsports.sdk.model.response.SnapshotResponse
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

    suspend fun schedule(request: ScheduleRequest.() -> Unit = {}): ScheduleResponse {
        val parameters = ScheduleRequest().apply(request)
        return get("/schedule/GetInPlaySchedule") {
            parameter("sportIds", parameters.sportIds.toString())
            parameter("providerIds", parameters.providerIds.toString())
        }
    }

    suspend fun orderFixtures(request: OrderFixturesRequest.() -> Unit = {}): OrderFixturesResponse {
        return get("/schedule/OrderFixtures") {
            parameter("sportIds", parameters.sportIds.toString())
            parameter("fixtureIds", parameters.fixtureIds.toString())
            parameter("providerIds", parameters.providerIds.toString())
        }
    }

    suspend fun cancelFixtureOrders(request: CancelFixtureOrdersRequest.() -> Unit = {}): CancelFixtureOrdersResponse {
        val parameters = CancelFixtureOrdersRequest().apply(request)
        return get("/schedule/CancelFixtureOrders") {
            parameter("sportIds", parameters.sportIds.toString())
            parameter("fixtureIds", parameters.fixtureIds.toString())
            parameter("providerIds", parameters.providerIds.toString())
        }
    }

    suspend fun orderedFixtures(request: OrderedFixturesRequest.() -> Unit = {}): Any {
        val parameters = OrderedFixturesRequest().apply(request)
        return get("/schedule/GetOrderedFixtures") {
            parameter("fixtureIds", parameters.fixtureIds.toString())
            parameter("providerIds", parameters.providerIds.toString())
            parameter("fromDate", parameters.fromDate)
            parameter("toDate", parameters.toDate)
        }
    }

    suspend fun snapshot(request: SnapshotRequest.() -> Unit = {}): SnapshotResponse {
        val parameters = SnapshotRequest().apply(request)
        return get("/Snapshot/GetSnapshotJson") {
            parameter("fixtureIds", parameters.fixtureIds.toString())
        }
    }

    suspend fun enablePackage(): Unit = get("/Package/EnablePackage")
    suspend fun disablePackage(): Unit = get("/Package/DisablePackage")

    private fun List<Any>?.toString() = this?.joinToString(",")
}
