package com.turbomates.kotlin.lsports.sdk.model.response

import com.turbomates.kotlin.lsports.sdk.model.Market
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.Response
import com.turbomates.kotlin.lsports.sdk.serializer.TimestampSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarketsResponse(
    @SerialName("Header")
    override val header: HeaderImpl,
    @SerialName("Body")
    val body: List<MarketImpl>
) : Response {
    @Serializable
    data class HeaderImpl(
        @SerialName("Type")
        override val type: Message.Type,
        @SerialName("ServerTimestamp")
        @Serializable(with = TimestampSerializer::class)
        override val serverTimestamp: LocalDateTime
    ) : Header

    @Serializable
    data class MarketImpl(
        @SerialName("Id")
        override val id: Long,
        @SerialName("Name")
        override val name: String
    ) : Market
}
