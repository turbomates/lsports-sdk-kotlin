package com.turbomates.kotlin.lsports.sdk.model.response

import com.turbomates.kotlin.lsports.sdk.model.Bet
import com.turbomates.kotlin.lsports.sdk.model.Body
import com.turbomates.kotlin.lsports.sdk.model.Event
import com.turbomates.kotlin.lsports.sdk.model.ExtraData
import com.turbomates.kotlin.lsports.sdk.model.Fixture
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.League
import com.turbomates.kotlin.lsports.sdk.model.Livescore
import com.turbomates.kotlin.lsports.sdk.model.Location
import com.turbomates.kotlin.lsports.sdk.model.Market
import com.turbomates.kotlin.lsports.sdk.model.Participant
import com.turbomates.kotlin.lsports.sdk.model.Provider
import com.turbomates.kotlin.lsports.sdk.model.Response
import com.turbomates.kotlin.lsports.sdk.model.Sport
import kotlinx.datetime.LocalDateTime
import java.util.UUID

data class SnapshotResponse(
    override val header: HeaderImpl,
    val body: BodyImpl
) : Response {
    data class HeaderImpl(
        override val type: Message.Type,
        override val serverTimestamp: LocalDateTime,
        val msgGuid: UUID
    ) : Header

    data class BodyImpl(
        val events: List<EventImpl>
    ) : Body

    data class EventImpl(
        override val fixtureId: Long,
        val fixture: FixtureImpl,
        val livescore: Livescore,
        val markets: MarketImpl
    ) : Event

    data class FixtureImpl(
        override val sport: Sport,
        override val location: Location,
        override val league: LeagueImpl,
        override val lastUpdate: LocalDateTime,
        override val startDate: LocalDateTime,
        override val status: Fixture.Status,
        override val participants: List<ParticipantImpl>,
        override val fixtureExtraData: ExtraData? = null,
        override val externalProviderId: Long? = null
    ) : Fixture

    data class LeagueImpl(
        override val id: Long,
        override val name: String
    ) : League

    data class ParticipantImpl(
        override val id: Long,
        override val name: String,
        override val rotationId: Long? = null,
        override val position: Int? = null,
        override val isActive: Boolean? = null,
        override val participantExtraData: ExtraData? = null

    ) : Participant

    data class MarketImpl(
        override val id: Long,
        override val name: String,
        val providers: List<ProviderImpl>
    ) : Market

    data class ProviderImpl(
        override val id: Long,
        override val name: String,
        override val lastUpdate: LocalDateTime,
        override val bets: List<BetImpl>,
        override val providerFixtureId: String,
        override val providerLeagueId: String? = null,
        override val providerMarketId: String? = null
    ) : Provider

    data class BetImpl(
        override val id: Long,
        override val name: String,
        override val line: String? = null,
        override val baseLine: String? = null,
        override val status: Bet.Status,
        override val startPrice: Double,
        override val price: Double,
        override val layPrice: Double? = null,
        override val priceVolume: Double? = null,
        override val layPriceVolume: Double? = null,
        override val providerBetId: String? = null,
        override val lastUpdate: LocalDateTime,
        val settlement: Bet.Settlement? = null
    ) : Bet
 }
