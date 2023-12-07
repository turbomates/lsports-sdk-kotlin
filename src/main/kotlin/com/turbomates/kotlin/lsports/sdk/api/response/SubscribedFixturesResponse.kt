package com.turbomates.kotlin.lsports.sdk.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubscribedFixturesResponse(
    @SerialName("Header")
    override val header: ApiResponse.Header,
    @SerialName("Body")
    override val body: Body
) : ApiResponse {
    @Serializable
    data class Body(
        @SerialName("Fixtures")
        val fixtureIds: List<Long>
    ) : ApiResponse.Body
}
