package com.turbomates.kotlin.lsports.sdk.api

import com.turbomates.kotlin.lsports.sdk.LSportsConfig
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.Events
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.FixtureMarkets
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.Fixtures
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.Leagues
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.OutrightFixtures
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.OutrightLeagues
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.Scores
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class PreMatchAPI(
    private val config: LSportsConfig
) {
    private suspend inline fun <reified T> get(
        path: String,
        parameters: HttpRequestBuilder.() -> Unit = {}
    ): T {
        return API.client.get(config.preMatchUrl + path) {
            parameter("username", config.username)
            parameter("password", config.password)
            parameter("guid", config.guid)
            parameters()
        }
    }

    suspend fun sports(): Any = get("/GetSports")
    suspend fun markets(): Any = get("/GetMarkets")
    suspend fun locations(): Any = get("/GetLocations")
    suspend fun bookmakers(): Any = get("/GetBookmakers")

    suspend fun leagues(request: Leagues): Any {
        return get("/GetLeagues") {
            parameter("sports", request.sportIds.toString())
            parameter("locations", request.locationIds.toString())
        }
    }

    suspend fun fixtures(request: Fixtures): Any {
        return get("/GetFixtures") {
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
        return get("/GetOutrightFixtures") {
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
            parameter("timestamp", request.timestamp)
            parameter("sports", request.sportIds.toString())
            parameter("locations", request.locationIds.toString())
            parameter("fixtures", request.fixtureIds.toString())
            parameter("statuses", request.statuses.toString())
        }
    }

    suspend fun fixtureMarkets(request: FixtureMarkets): Any {
        return get("/GetFixtureMarkets") {
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

    suspend fun enablePackage(): Unit = get("/EnablePackage")
    suspend fun disablePackage(): Unit = get("/DisablePackage")

    private fun List<Any>?.toString() = this?.joinToString(",")
}
