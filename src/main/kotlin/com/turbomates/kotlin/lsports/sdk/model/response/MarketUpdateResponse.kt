package com.turbomates.kotlin.lsports.sdk.model.response

import com.turbomates.kotlin.lsports.sdk.model.Bet
import com.turbomates.kotlin.lsports.sdk.model.Event
import com.turbomates.kotlin.lsports.sdk.model.Market
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.Provider
import com.turbomates.kotlin.lsports.sdk.model.Response
import com.turbomates.kotlin.lsports.sdk.serializer.LocalDateTimeSerializer
import com.turbomates.kotlin.lsports.sdk.serializer.TimestampSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarketUpdateResponse(
    @SerialName("Header")
    override val header: HeaderImpl,
    @SerialName("Body")
    val body: List<EventImpl>
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
    data class EventImpl(
        @SerialName("FixtureId")
        override val fixtureId: Long,
        @SerialName("Markets")
        val markets: List<MarketImpl>
    ) : Event

    @Serializable
    data class MarketImpl(
        @SerialName("Id")
        override val id: Long,
        @SerialName("Name")
        override val name: String,
        @SerialName("Providers")
        val providers: List<ProviderImpl>
    ) : Market

    @Serializable
    data class ProviderImpl(
        @SerialName("Id")
        override val id: Long,
        @SerialName("Name")
        override val name: String,
        @SerialName("LastUpdate")
        @Serializable(with = LocalDateTimeSerializer::class)
        override val lastUpdate: LocalDateTime,
        @SerialName("Bets")
        override val bets: List<BetImpl>? = null,
        @SerialName("ProviderFixtureId")
        override val providerFixtureId: String? = null,
        @SerialName("ProviderLeagueId")
        override val providerLeagueId: String? = null,
        @SerialName("ProviderMarketId")
        override val providerMarketId: String? = null
    ) : Provider

    @Serializable
    data class BetImpl(
        @SerialName("Id")
        override val id: Long,
        @SerialName("Name")
        override val name: String,
        @SerialName("Line")
        override val line: String? = null,
        @SerialName("BaseLine")
        override val baseLine: String? = null,
        @SerialName("Status")
        override val status: Bet.Status,
        @SerialName("StartPrice")
        override val startPrice: Double,
        @SerialName("Price")
        override val price: Double,
        @SerialName("LayPrice")
        override val layPrice: Double? = null,
        @SerialName("PriceVolume")
        override val priceVolume: Double? = null,
        @SerialName("LayPriceVolume")
        override val layPriceVolume: Double? = null,
        @SerialName("ProviderBetId")
        override val providerBetId: String? = null,
        @SerialName("ParticipantId")
        override val participantId: Long? = null,
        @SerialName("LastUpdate")
        @Serializable(with = LocalDateTimeSerializer::class)
        override val lastUpdate: LocalDateTime,
        @SerialName("Settlement")
        val settlement: Bet.Settlement? = null
    ) : Bet
}
