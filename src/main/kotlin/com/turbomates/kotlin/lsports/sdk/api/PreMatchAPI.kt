package com.turbomates.kotlin.lsports.sdk.api

import com.turbomates.kotlin.lsports.sdk.LSportsConfig
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.Bookmakers
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.Events
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.FixtureMarkets
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.Fixtures
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.Leagues
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.Locations
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.Markets
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.OutrightFixtures
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.OutrightLeagues
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.Scores
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.Sports
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

class PreMatchAPI (
    private val config: LSportsConfig
) {
    private suspend inline fun <reified T> get(
        path: String,
        parameters: HttpRequestBuilder.() -> Unit
    ): T {
        return client.get(config.preMatchUrl + path) {
            parameter("username", config.username)
            parameter("password", config.password)
            parameters()
        }
    }

    suspend fun sports(request: Sports): Any {
        return get("/GetSports") {
            parameter("guid", request.guid)
        }
    }

    suspend fun locations(request: Locations): Any {
        return get("/GetLocations") {
            parameter("guid", request.guid)
        }
    }

    suspend fun leagues(request: Leagues): Any {
        return get("/GetLeagues") {
            parameter("guid", request.guid)
            parameter("sports", request.sportIds.toString())
            parameter("locations", request.locationIds.toString())
        }
    }

    suspend fun bookmakers(request: Bookmakers): Any {
        return get("/GetBookmakers") {
            parameter("guid", request.guid)
        }
    }

    suspend fun markets(request: Markets): Any {
        return get("/GetMarkets") {
            parameter("guid", request.guid)
        }
    }

    suspend fun fixtures(request: Fixtures): Any {
        return get("/GetFixtures") {
            parameter("guid", request.guid)
            parameter("timestamp", request.timestamp)
            parameter("fromDate", request.fromDate)
            parameter("toDate", request.toDate)
            parameter("sports", request.sportIds.toString())
            parameter("locations", request.locationIds.toString())
            parameter("leagues", request.leagueIds.toString())
            parameter("fixtures", request.fixtureIds.toString())
            parameter("statuses", request.statuses.toString())
        }
    }

    suspend fun outrightFixtures(request: OutrightFixtures): Any {
       return get<JsonElement>("/GetOutrightFixtures") {
            parameter("guid", request.guid)
            parameter("timestamp", request.timestamp)
            parameter("fromDate", request.fromDate)
            parameter("toDate", request.toDate)
            parameter("sports", request.sportIds.toString())
            parameter("locations", request.locationIds.toString())
            parameter("fixtures", request.fixtureIds.toString())
            parameter("statuses", request.statuses.toString())
        }
    }

    suspend fun outrightLeagues(request: OutrightLeagues): Any {
        return get("/GetOutrightLeagues") {
            parameter("guid", request.guid)
            parameter("timestamp", request.timestamp)
            parameter("sports", request.sportIds.toString())
            parameter("locations", request.locationIds.toString())
            parameter("fixtures", request.fixtureIds.toString())
            parameter("statuses", request.statuses.toString())
        }
    }

    suspend fun fixtureMarkets(request: FixtureMarkets): Any {
        return get("/GetFixtureMarkets") {
            parameter("guid", request.guid)
            parameter("timestamp", request.timestamp)
            parameter("fromDate", request.fromDate)
            parameter("toDate", request.toDate)
            parameter("sports", request.sportIds.toString())
            parameter("locations", request.locationIds.toString())
            parameter("leagues", request.leagueIds.toString())
            parameter("fixtures", request.fixtureIds.toString())
            parameter("statuses", request.statuses.toString())
            parameter("bookmakers", request.bookmakerIds.toString())
            parameter("markets", request.marketIds.toString())
        }
    }

    suspend fun scores(request: Scores): Any {
        return get("/GetScores") {
            parameter("guid", request.guid)
            parameter("timestamp", request.timestamp)
            parameter("fromDate", request.fromDate)
            parameter("toDate", request.toDate)
            parameter("sports", request.sportIds.toString())
            parameter("locations", request.locationIds.toString())
            parameter("leagues", request.leagueIds.toString())
            parameter("fixtures", request.fixtureIds.toString())
            parameter("statuses", request.statuses.toString())
        }
    }

    suspend fun events(request: Events): Any {
        return get("/GetEvents") {
            parameter("guid", request.guid)
            parameter("timestamp", request.timestamp)
            parameter("fromDate", request.fromDate)
            parameter("toDate", request.toDate)
            parameter("sports", request.sportIds.toString())
            parameter("locations", request.locationIds.toString())
            parameter("leagues", request.leagueIds.toString())
            parameter("fixtures", request.fixtureIds.toString())
            parameter("statuses", request.statuses.toString())
            parameter("bookmakers", request.bookmakerIds.toString())
            parameter("markets", request.marketIds.toString())
        }
    }

    suspend fun enablePackage(guid: String) {
        return get("/EnablePackage") {
            parameter("guid", guid)
        }
    }

    suspend fun disablePackage(guid: String) {
        return get("/DisablePackage") {
            parameter("guid", guid)
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
