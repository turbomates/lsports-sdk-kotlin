package com.turbomates.kotlin.lsports.sdk.api.prematch

import com.turbomates.kotlin.lsports.sdk.LSportsConfig
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.BookmakersRequest
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.EventsRequest
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.FixtureMarketsRequest
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.FixturesRequest
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.LeaguesRequest
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.LocationsRequest
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.MarketsRequest
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.OutrightFixturesRequest
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.OutrightLeaguesRequest
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.ScoresRequest
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.SportsRequest
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import kotlinx.serialization.json.Json

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

    suspend fun sports(request: SportsRequest): Any {
        return get("/GetSports") {
            parameter("guid", request.guid)
        }
    }

    suspend fun locations(request: LocationsRequest): Any {
        return get("/GetLocations") {
            parameter("guid", request.guid)
        }
    }

    suspend fun leagues(request: LeaguesRequest): Any {
        return get("/GetLeagues") {
            parameter("guid", request.guid)
            parameter("sports", request.sportIds.toString())
            parameter("locations", request.locationIds.toString())
        }
    }

    suspend fun bookmakers(request: BookmakersRequest): Any {
        return get("/GetBookmakers") {
            parameter("guid", request.guid)
        }
    }

    suspend fun markets(request: MarketsRequest): Any {
        return get("/GetMarkets") {
            parameter("guid", request.guid)
        }
    }

    suspend fun fixtures(request: FixturesRequest): Any {
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

    suspend fun outrightFixtures(request: OutrightFixturesRequest): Any {
       return get<String>("/GetOutrightFixtures") {
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

    suspend fun outrightLeagues(request: OutrightLeaguesRequest): Any {
        return get("/GetOutrightLeagues") {
            parameter("guid", request.guid)
            parameter("timestamp", request.timestamp)
            parameter("sports", request.sportIds.toString())
            parameter("locations", request.locationIds.toString())
            parameter("fixtures", request.fixtureIds.toString())
            parameter("statuses", request.statuses.toString())
        }
    }

    suspend fun fixtureMarkets(request: FixtureMarketsRequest): Any {
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

    suspend fun scores(request: ScoresRequest): Any {
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

    suspend fun events(request: EventsRequest): Any {
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
