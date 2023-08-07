package com.turbomates.kotlin.lsports.sdk.api.response

import com.turbomates.kotlin.lsports.sdk.model.Competition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompetitionsResponse(
    @SerialName("Header")
    override val header: ApiResponse.Header,
    @SerialName("Body")
    override val body: Body
) : ApiResponse {
    @Serializable
    data class Body(
        @SerialName("Competitions")
        val competitions: List<Competition>
    ) : ApiResponse.Body
}
