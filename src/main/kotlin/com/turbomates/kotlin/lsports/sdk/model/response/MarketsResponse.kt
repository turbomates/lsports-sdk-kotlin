package com.turbomates.kotlin.lsports.sdk.model.response

import com.turbomates.kotlin.lsports.sdk.model.Market
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.Response
import kotlinx.datetime.LocalDateTime

data class MarketsResponse(
    override val header: HeaderImpl,
    val body: List<MarketImpl>
) : Response {
    data class HeaderImpl(
        override val type: Message.Type,
        override val serverTimestamp: LocalDateTime
    ) : Header

    data class MarketImpl(
        override val id: Long,
        override val name: String
    ) : Market
}
