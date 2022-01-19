package com.turbomates.kotlin.lsports.sdk.model.message

import com.turbomates.kotlin.lsports.sdk.model.Bet
import com.turbomates.kotlin.lsports.sdk.model.Body
import com.turbomates.kotlin.lsports.sdk.model.Event
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.Market
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Provider
import java.time.LocalDateTime
import java.util.UUID

data class SettlementMessage(
    override val header: HeaderImpl,
    val body: BodyImpl
) : Message {
    data class HeaderImpl(
        override val type: Message.Type,
        override val serverTimestamp: LocalDateTime,
        val msgGuid: UUID,
        val id: Long
    ) : Header

    data class BodyImpl(
        val events: List<EventImpl>
    ) : Body

    data class EventImpl(
        override val fixtureId: Long,
        val markets: List<MarketImpl>
    ) : Event

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
        override val providerFixtureId: String? = null,
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
        val settlement: Bet.Settlement
    ) : Bet
}