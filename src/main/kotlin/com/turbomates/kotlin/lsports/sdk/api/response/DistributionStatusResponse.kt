package com.turbomates.kotlin.lsports.sdk.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DistributionStatusResponse(
    @SerialName("Header")
    override val header: ApiResponse.Header,
    @SerialName("Body")
    override val body: Body
) : ApiResponse {
    @Serializable
    data class Body(
        @SerialName("IsDistributionOn")
        val isDistributionOn: Boolean,
        @SerialName("Consumers")
        val consumers: List<String>,
        @SerialName("NumberMessagesInQueue")
        val numberMessagesInQueue: Double,
        @SerialName("MessagesPerSecond")
        val messagesPerSecond: Double
    ) : ApiResponse.Body
}
