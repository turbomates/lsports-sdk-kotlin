package com.turbomates.kotlin.lsports.sdk.model.response

import com.turbomates.kotlin.lsports.sdk.model.Bet
import com.turbomates.kotlin.lsports.sdk.model.Event
import com.turbomates.kotlin.lsports.sdk.model.ExtraData
import com.turbomates.kotlin.lsports.sdk.model.Fixture
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.League
import com.turbomates.kotlin.lsports.sdk.model.Livescore
import com.turbomates.kotlin.lsports.sdk.model.Location
import com.turbomates.kotlin.lsports.sdk.model.Market
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Participant
import com.turbomates.kotlin.lsports.sdk.model.Provider
import com.turbomates.kotlin.lsports.sdk.model.Response
import com.turbomates.kotlin.lsports.sdk.model.Sport
import com.turbomates.kotlin.lsports.sdk.serializer.LocalDateTimeSerializer
import com.turbomates.kotlin.lsports.sdk.serializer.TimestampSerializer
import java.time.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FullEventResponse(
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
        @SerialName("Fixture")
        val fixture: FixtureImpl,
        @SerialName("Livescore")
        val livescore: Livescore? = null,
        @SerialName("Markets")
        val markets: List<MarketImpl>? = null
    ) : Event

    @Serializable
    data class FixtureImpl(
        @SerialName("Sport")
        override val sport: Sport,
        @SerialName("Location")
        override val location: Location,
        @SerialName("League")
        override val league: LeagueImpl,
        @SerialName("LastUpdate")
        @Serializable(with = LocalDateTimeSerializer::class)
        override val lastUpdate: LocalDateTime,
        @SerialName("StartDate")
        @Serializable(with = LocalDateTimeSerializer::class)
        override val startDate: LocalDateTime,
        @SerialName("Status")
        override val status: Fixture.Status,
        @SerialName("Participants")
        override val participants: List<ParticipantImpl>,
        @SerialName("FixtureExtraData")
        override val fixtureExtraData: ExtraData? = null,
        @SerialName("ExternalProviderId")
        override val externalProviderId: Long? = null
    ) : Fixture

    @Serializable
    data class LeagueImpl(
        @SerialName("Id")
        override val id: Long,
        @SerialName("Name")
        override val name: String
    ) : League

    @Serializable
    data class ParticipantImpl(
        @SerialName("Id")
        override val id: Long,
        @SerialName("Name")
        override val name: String,
        @SerialName("Position")
        override val position: Int,
        @SerialName("RotationId")
        override val rotationId: Long? = null,
        @SerialName("IsActive")
        override val isActive: Boolean? = null,
        @SerialName("ParticipantExtraData")
        override val participantExtraData: ExtraData? = null
    ) : Participant

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
        val settlement: Bet.Settlement? = null
    ) : Bet

}
