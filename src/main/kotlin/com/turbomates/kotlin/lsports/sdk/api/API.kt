package com.turbomates.kotlin.lsports.sdk.api

import com.turbomates.kotlin.lsports.sdk.LSportsConfig
import com.turbomates.kotlin.lsports.sdk.api.request.CompetitionsRequest
import com.turbomates.kotlin.lsports.sdk.api.request.LeaguesRequest
import com.turbomates.kotlin.lsports.sdk.api.request.MarketsRequest
import com.turbomates.kotlin.lsports.sdk.api.request.Request
import com.turbomates.kotlin.lsports.sdk.api.request.TranslationRequest
import com.turbomates.kotlin.lsports.sdk.api.response.CompetitionsResponse
import com.turbomates.kotlin.lsports.sdk.api.response.DistributionResponse
import com.turbomates.kotlin.lsports.sdk.api.response.DistributionStatusResponse
import com.turbomates.kotlin.lsports.sdk.api.response.LeaguesResponse
import com.turbomates.kotlin.lsports.sdk.api.response.LocationsResponse
import com.turbomates.kotlin.lsports.sdk.api.response.MarketsResponse
import com.turbomates.kotlin.lsports.sdk.api.response.Response
import com.turbomates.kotlin.lsports.sdk.api.response.SportsResponse
import com.turbomates.kotlin.lsports.sdk.api.response.TracksResponse
import com.turbomates.kotlin.lsports.sdk.api.response.TranslationResponse
import com.turbomates.kotlin.lsports.sdk.model.Language
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

abstract class API(val config: LSportsConfig, val type: Type) {
    protected val client = HttpClient(CIO)
    protected val json = Json {
        prettyPrint = false
        encodeDefaults = true
        explicitNulls = false
        ignoreUnknownKeys = true
    }

    protected suspend inline fun <reified T : Response> request(url: String): T = request(url, Request())
    protected suspend inline fun <reified T : Response, reified U : Request> request(url: String, request: U): T {
        request.username = config.username
        request.password = config.password
        request.packageId = when (type) {
            Type.PRE_MATCH -> config.preMatchPackageId
            Type.IN_PLAY -> config.inPlayPackageId
        }

        val response = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(json.encodeToString(request))
        }

        val content = response.bodyAsText()
        println(content)
        if (response.status != HttpStatusCode.OK) throw LSportsClientAPIException("LSports response: ${response.status}, message: $content")
        return json.decodeFromString(content)
    }

    protected suspend inline fun <reified T : Response, reified U : Request> snapshotRequest(request: U, action: SnapshotAction): T =
        request("${config.snapshotApiUrl}/${type.value}/${action.value}", request)

    suspend fun distributionStart(): DistributionResponse =
        request("${config.apiUrl}/Distribution/Start")

    suspend fun distributionStop(): DistributionResponse =
        request("${config.apiUrl}/Distribution/Stop")

    suspend fun distributionStatus(): DistributionStatusResponse =
        request("${config.apiUrl}/Package/GetDistributionStatus")

    suspend fun translations(languages: List<Language>, requestBlock: TranslationRequest.() -> Unit = {}): TranslationResponse =
        request("${config.apiUrl}/Translation/Get", TranslationRequest(languages).apply(requestBlock))

    suspend fun sports(): SportsResponse =
        request("${config.apiUrl}/Sports/Get")

    suspend fun locations(): LocationsResponse =
        request("${config.apiUrl}/Locations/Get")

    suspend fun leagues(requestBlock: LeaguesRequest.() -> Unit = {}): LeaguesResponse =
        request("${config.apiUrl}/Leagues/Get", LeaguesRequest().apply(requestBlock))

    suspend fun markets(requestBlock: MarketsRequest.() -> Unit = {}): MarketsResponse =
        request("${config.apiUrl}/Markets/Get", MarketsRequest().apply(requestBlock))

    suspend fun outrightCompetitions(requestBlock: CompetitionsRequest.() -> Unit): CompetitionsResponse =
        request("${config.apiUrl}/Outright/GetCompetitions", CompetitionsRequest().apply(requestBlock))

    suspend fun outrightTracks(): TracksResponse =
        request("${config.apiUrl}/Outright/GetAllTracks")

    enum class Type(val value: String) {
        PRE_MATCH("PreMatch"),
        IN_PLAY("InPlay")
    }

    interface SnapshotAction {
        val value: String
    }
}

class LSportsClientAPIException(message: String) : Exception(message)
