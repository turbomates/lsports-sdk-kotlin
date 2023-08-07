package com.turbomates.kotlin.lsports.sdk.api.response

import com.turbomates.kotlin.lsports.sdk.model.Language
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TranslationResponse(
    @SerialName("Header")
    override val header: ApiResponse.Header,
    @SerialName("Body")
    override val body: Body
) : ApiResponse {
    @Serializable
    data class Body(
        @SerialName("Sports")
        val sports: Map<Long, List<Translation>>? = null,
        @SerialName("Locations")
        val locations: Map<Long, List<Translation>>? = null,
        @SerialName("Leagues")
        val leagues: Map<Long, List<Translation>>? = null,
        @SerialName("Markets")
        val markets: Map<Long, List<Translation>>? = null,
        @SerialName("Participants")
        val participants: Map<Long, List<Translation>>? = null,
    ) : ApiResponse.Body

    @Serializable
    data class Translation(
        @SerialName("LanguageId")
        val language: Language,
        @SerialName("Value")
        val value: String
    )
}
