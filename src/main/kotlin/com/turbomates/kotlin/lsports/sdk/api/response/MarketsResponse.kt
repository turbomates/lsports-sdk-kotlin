package com.turbomates.kotlin.lsports.sdk.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarketsResponse(
    @SerialName("Header")
    override val header: ApiResponse.Header,
    @SerialName("Body")
    override val body: Body
) : ApiResponse {
    @Serializable
    data class Body(
        @SerialName("Markets")
        val markets: List<Market>
    ) : ApiResponse.Body

    @Serializable
    data class Market(
        @SerialName("Id")
        val id: Long,
        @SerialName("Name")
        val name: String,
        @SerialName("IsSettleable")
        val isSettleable: Boolean
    )
}
