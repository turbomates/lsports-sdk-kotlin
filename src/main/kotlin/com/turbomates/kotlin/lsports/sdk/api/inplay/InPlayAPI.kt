package com.turbomates.kotlin.lsports.sdk.api.inplay

import com.turbomates.kotlin.lsports.sdk.LSportsConfig
import com.turbomates.kotlin.lsports.sdk.api.inplay.request.CancelOrderRequest
import com.turbomates.kotlin.lsports.sdk.api.inplay.request.OrderRequest
import com.turbomates.kotlin.lsports.sdk.api.inplay.request.ScheduleRequest
import com.turbomates.kotlin.lsports.sdk.api.inplay.request.SnapshotRequest
import com.turbomates.kotlin.lsports.sdk.api.inplay.request.ViewOrderedRequest
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import kotlinx.serialization.json.Json

class InPlayAPI(
    private val config: LSportsConfig
) {
    private suspend inline fun <reified T> get(
        path: String,
        parameters: HttpRequestBuilder.() -> Unit
    ): T {
        return client.get(config.inPlayUrl + path) {
            parameter("username", config.username)
            parameter("password", config.password)
            parameters()
        }
    }

    suspend fun schedule(request: ScheduleRequest): Any {
        return get("/schedule/GetInPlaySchedule") {
            parameter("packageid", request.packageId)
            parameter("sportIds", request.sportIds.toString())
            parameter("providerIds", request.providerIds.toString())
        }
    }

    suspend fun order(request: OrderRequest): Any {
        return get<String>("/schedule/OrderFixtures") {
            parameter("packageid", request.packageId)
            parameter("sportIds", request.sportIds.toString())
            parameter("fixtureIds", request.fixtureIds.toString())
            parameter("providerIds", request.providerIds.toString())
        }
    }

    suspend fun cancelOrder(request: CancelOrderRequest): Any {
        return get<String>("/schedule/CancelFixtureOrders") {
            parameter("packageid", request.packageId)
            parameter("sportIds", request.sportIds.toString())
            parameter("fixtureIds", request.fixtureIds.toString())
            parameter("providerIds", request.providerIds.toString())
        }
    }

    suspend fun viewOrdered(request: ViewOrderedRequest): Any {
        return get("/schedule/GetOrderedFixtures") {
            parameter("packageid", request.packageId)
            parameter("fixtureIds", request.fixtureIds.toString())
            parameter("providerIds", request.providerIds.toString())
            parameter("fromDate", request.fromDate)
            parameter("toDate", request.toDate)
        }
    }

    suspend fun snapshot(request: SnapshotRequest): Any {
        return get<String>("/Snapshot/GetSnapshotJson") {
            parameter("packageid", request.packageId)
            parameter("fixtureIds", request.fixtureIds.toString())
        }
    }

    suspend fun enablePackage(packageId: String) {
        return get("/Package/EnablePackage") {
            parameter("packageid", packageId)
        }
    }

    suspend fun disablePackage(packageId: String) {
        return get("/Package/DisablePackage") {
            parameter("packageid", packageId)
        }
    }

    companion object {
        private fun List<Any>?.toString() = this?.joinToString(",")

        private val client: HttpClient = HttpClient(CIO) {
            install(JsonFeature) {
                accept(ContentType.Application.Json)
                accept(ContentType("application", "octet-stream"))
                serializer = KotlinxSerializer(
                    Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
}
