package com.turbomates.kotlin.lsports.sdk.api

import com.turbomates.kotlin.lsports.sdk.LSportConfig
import com.turbomates.kotlin.lsports.sdk.infrastructure.client
import com.turbomates.kotlin.lsports.sdk.model.Bookmaker
import com.turbomates.kotlin.lsports.sdk.model.League
import com.turbomates.kotlin.lsports.sdk.model.Location
import com.turbomates.kotlin.lsports.sdk.model.Market
import com.turbomates.kotlin.lsports.sdk.model.Sport
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class LSportPreLiveAPI (
    private val config: LSportConfig
) {
    suspend fun sports(guid: String): List<Sport> {
        return client.get<SportResponse>(SPORTS) {
            parameter("username", config.username)
            parameter("password", config.password)
            parameter("guid", guid)
        }.sports
    }

    suspend fun locations(guid: String): List<Location> {
         return client.get<LocationResponse>(LOCATIONS) {
            parameter("username", config.username)
            parameter("password", config.password)
            parameter("guid", guid)
        }.locations
    }

    suspend fun leagues(guid: String): List<League> {
        return client.get<LeagueResponse>(LEAGUES) {
            parameter("username", config.username)
            parameter("password", config.password)
            parameter("guid", guid)
        }.leagues
    }

    suspend fun bookmakers(guid: String): List<Bookmaker> {
        return client.get<BookmakerResponse>(BOOKMAKERS) {
            parameter("username", config.username)
            parameter("password", config.password)
            parameter("guid", guid)
        }.bookmakers
    }

    suspend fun markets(guid: String): List<Market> {
        return client.get<MarketResponse>(MARKETS) {
            parameter("username", config.username)
            parameter("password", config.password)
            parameter("guid", guid)
        }.markets
    }

    companion object {
        private const val SPORTS = "https://prematch.lsports.eu/OddService/GetSports"
        private const val LOCATIONS = "https://prematch.lsports.eu/OddService/GetLocations"
        private const val LEAGUES = "https://prematch.lsports.eu/OddService/GetLeagues"
        private const val BOOKMAKERS = "https://prematch.lsports.eu/OddService/GetBookmakers"
        private const val MARKETS = "https://prematch.lsports.eu/OddService/GetMarkets"
    }
}

@Serializable
data class SportResponse(
    @SerialName("Body")
    val sports: List<Sport>
)

@Serializable
data class LocationResponse(
    @SerialName("Body")
    val locations: List<Location>
)

@Serializable
data class LeagueResponse(
    @SerialName("Body")
    val leagues: List<League>
)

@Serializable
data class BookmakerResponse(
    @SerialName("Body")
    val bookmakers: List<Bookmaker>
)

@Serializable
data class MarketResponse(
    @SerialName("Body")
    val markets: List<Market>
)
