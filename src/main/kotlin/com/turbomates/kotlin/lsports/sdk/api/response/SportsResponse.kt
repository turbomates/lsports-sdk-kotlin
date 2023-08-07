package com.turbomates.kotlin.lsports.sdk.api.response

import com.turbomates.kotlin.lsports.sdk.model.Sport
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SportsResponse(
    @SerialName("Header")
    override val header: ApiResponse.Header,
    @SerialName("Body")
    override val body: Body
) : ApiResponse {
    @Serializable
    data class Body(
        @SerialName("Sports")
        val sports: List<Sport>
    ) : ApiResponse.Body
}
