package com.turbomates.kotlin.lsports.sdk.model.message

import com.turbomates.kotlin.lsports.sdk.model.Bet
import com.turbomates.kotlin.lsports.sdk.model.Body
import com.turbomates.kotlin.lsports.sdk.model.Event
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.Livescore
import com.turbomates.kotlin.lsports.sdk.model.Market
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Provider
import com.turbomates.kotlin.lsports.sdk.serializer.LocalDateTimeSerializer
import com.turbomates.kotlin.lsports.sdk.serializer.TimestampSerializer
import com.turbomates.kotlin.lsports.sdk.serializer.UUIDSerializer
import kotlinx.datetime.LocalDateTime
import java.util.UUID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SettlementMessage(
    @SerialName("Header")
    override val header: HeaderImpl,
    @SerialName("Body")
    val body: BodyImpl
) : Message {
    @Serializable
    data class HeaderImpl(
        @SerialName("Type")
        override val type: Message.Type,
        @SerialName("ServerTimestamp")
        @Serializable(with = TimestampSerializer::class)
        override val serverTimestamp: LocalDateTime,
        @SerialName("MsgGuid")
        @Serializable(with = UUIDSerializer::class)
        val msgGuid: UUID,
        @SerialName("MsgId")
        val msgId: Long
    ) : Header

    @Serializable
    data class BodyImpl(
        @SerialName("Events")
        val events: List<EventImpl>
    ) : Body

    @Serializable
    data class EventImpl(
        @SerialName("FixtureId")
        override val fixtureId: Long,
        @SerialName("Markets")
        val markets: List<MarketImpl>,
        @SerialName("Livescore")
        val livescore: Livescore? = null
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
        override val bets: List<BetImpl>,
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
        val settlement: Bet.Settlement
    ) : Bet
}
