package com.turbomates.kotlin.lsports.sdk.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeaguesResponse(
    @SerialName("Header")
    override val header: ApiResponse.Header,
    @SerialName("Body")
    override val body: Body
) : ApiResponse {
    @Serializable
    data class Body(
        @SerialName("Leagues")
        val leagues: List<League>
    ) : ApiResponse.Body

    @Serializable
    data class League(
        @SerialName("Id")
        val id: Long,
        @SerialName("Name")
        val name: String,
        @SerialName("SportId")
        val sportId: Long,
        @SerialName("LocationId")
        val locationId: Long
    )
}
