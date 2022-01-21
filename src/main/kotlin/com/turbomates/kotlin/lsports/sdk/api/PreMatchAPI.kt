package com.turbomates.kotlin.lsports.sdk.api

import com.turbomates.kotlin.lsports.sdk.LSportsConfig
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.EventsRequest
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.FixtureMarketsRequest
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.FixturesRequest
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.LeaguesRequest
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.OutrightFixturesRequest
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.OutrightLeaguesRequest
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.ScoresRequest
import com.turbomates.kotlin.lsports.sdk.model.response.BookmakersResponse
import com.turbomates.kotlin.lsports.sdk.model.response.FixtureUpdateResponse
import com.turbomates.kotlin.lsports.sdk.model.response.FullEventResponse
import com.turbomates.kotlin.lsports.sdk.model.response.LeaguesResponse
import com.turbomates.kotlin.lsports.sdk.model.response.LivescoreUpdateResponse
import com.turbomates.kotlin.lsports.sdk.model.response.LocationsResponse
import com.turbomates.kotlin.lsports.sdk.model.response.MarketUpdateResponse
import com.turbomates.kotlin.lsports.sdk.model.response.MarketsResponse
import com.turbomates.kotlin.lsports.sdk.model.response.OutrightFixturesResponse
import com.turbomates.kotlin.lsports.sdk.model.response.OutrightLeaguesResponse
import com.turbomates.kotlin.lsports.sdk.model.response.SportsResponse
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

    suspend fun sports(): SportsResponse = get("/GetSports")
    suspend fun markets(): MarketsResponse = get("/GetMarkets")
    suspend fun locations(): LocationsResponse = get("/GetLocations")
    suspend fun bookmakers(): BookmakersResponse = get("/GetBookmakers")

    suspend fun leagues(request: LeaguesRequest.() -> Unit = {}): LeaguesResponse {
        val parameters = LeaguesRequest().apply(request)
        return get("/GetLeagues") {
            parameter("sports", parameters.sportIds.toString())
            parameter("locations", parameters.locationIds.toString())
        }
    }

    suspend fun fixtures(request: FixturesRequest.() -> Unit = {}): FixtureUpdateResponse {
        val parameters = FixturesRequest().apply(request)
        return get("/GetFixtures") {
            parameter("timestamp", parameters.timestamp)
            parameter("fromDate", parameters.fromDate)
            parameter("toDate", parameters.toDate)
            parameter("sports", parameters.sportIds.toString())
            parameter("locations", parameters.locationIds.toString())
            parameter("leagues", parameters.leagueIds.toString())
            parameter("fixtures", parameters.fixtureIds.toString())
            parameter("statuses", parameters.statuses.toString())
        }
    }

    suspend fun outrightFixtures(request: OutrightFixturesRequest.() -> Unit = {}): OutrightFixturesResponse {
        val parameters = OutrightFixturesRequest().apply(request)
        return get("/GetOutrightFixtures") {
            parameter("timestamp", parameters.timestamp)
            parameter("fromDate", parameters.fromDate)
            parameter("toDate", parameters.toDate)
            parameter("sports", parameters.sportIds.toString())
            parameter("locations", parameters.locationIds.toString())
            parameter("fixtures", parameters.fixtureIds.toString())
            parameter("statuses", parameters.statuses.toString())
        }
    }

    suspend fun outrightLeagues(request: OutrightLeaguesRequest.() -> Unit = {}): OutrightLeaguesResponse {
        val parameters = OutrightLeaguesRequest().apply(request)
        return get("/GetOutrightLeagues") {
            parameter("timestamp", parameters.timestamp)
            parameter("sports", parameters.sportIds.toString())
            parameter("locations", parameters.locationIds.toString())
            parameter("fixtures", parameters.fixtureIds.toString())
            parameter("statuses", parameters.statuses.toString())
        }
    }

    suspend fun fixtureMarkets(request: FixtureMarketsRequest.() -> Unit = {}): MarketUpdateResponse {
        val parameters = FixtureMarketsRequest().apply(request)
        return get("/GetFixtureMarkets") {
            parameter("timestamp", parameters.timestamp)
            parameter("fromDate", parameters.fromDate)
            parameter("toDate", parameters.toDate)
            parameter("sports", parameters.sportIds.toString())
            parameter("locations", parameters.locationIds.toString())
            parameter("leagues", parameters.leagueIds.toString())
            parameter("fixtures", parameters.fixtureIds.toString())
            parameter("statuses", parameters.statuses.toString())
            parameter("bookmakers", parameters.bookmakerIds.toString())
            parameter("markets", parameters.marketIds.toString())
        }
    }

    suspend fun scores(request: ScoresRequest.() -> Unit = {}): LivescoreUpdateResponse {
        val parameters = ScoresRequest().apply(request)
        return get("/GetScores") {
            parameter("timestamp", parameters.timestamp)
            parameter("fromDate", parameters.fromDate)
            parameter("toDate", parameters.toDate)
            parameter("sports", parameters.sportIds.toString())
            parameter("locations", parameters.locationIds.toString())
            parameter("leagues", parameters.leagueIds.toString())
            parameter("fixtures", parameters.fixtureIds.toString())
            parameter("statuses", parameters.statuses.toString())
        }
    }

    suspend fun events(request: EventsRequest.() -> Unit = {}): FullEventResponse {
        val parameters = EventsRequest().apply(request)
        return get("/GetEvents") {
            parameter("timestamp", parameters.timestamp)
            parameter("fromDate", parameters.fromDate)
            parameter("toDate", parameters.toDate)
            parameter("sports", parameters.sportIds.toString())
            parameter("locations", parameters.locationIds.toString())
            parameter("leagues", parameters.leagueIds.toString())
            parameter("fixtures", parameters.fixtureIds.toString())
            parameter("statuses", parameters.statuses.toString())
            parameter("bookmakers", parameters.bookmakerIds.toString())
            parameter("markets", parameters.marketIds.toString())
        }
    }

    suspend fun enablePackage(): Unit = get("/EnablePackage")
    suspend fun disablePackage(): Unit = get("/DisablePackage")

    private fun List<Any>?.toString() = this?.joinToString(",")
}
