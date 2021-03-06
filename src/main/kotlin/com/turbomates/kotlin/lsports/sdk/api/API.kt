package com.turbomates.kotlin.lsports.sdk.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.ContentType
import kotlinx.serialization.json.Json

object API {
    val client: HttpClient = HttpClient(CIO) {
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
