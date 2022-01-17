package com.turbomates.kotlin.lsports.sdk.model.response

import com.turbomates.kotlin.lsports.sdk.model.Bet
import com.turbomates.kotlin.lsports.sdk.model.Body
import com.turbomates.kotlin.lsports.sdk.model.Event
import com.turbomates.kotlin.lsports.sdk.model.ExtraData
import com.turbomates.kotlin.lsports.sdk.model.Fixture
import com.turbomates.kotlin.lsports.sdk.model.League
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.Location
import com.turbomates.kotlin.lsports.sdk.model.Participant
import com.turbomates.kotlin.lsports.sdk.model.Provider
import com.turbomates.kotlin.lsports.sdk.model.Response
import com.turbomates.kotlin.lsports.sdk.model.Sport
import java.time.LocalDateTime
import java.util.UUID

data class ScheduleResponse(
    override val header: HeaderImpl,
    val body: BodyImpl
) : Response {
    data class HeaderImpl(
        override val type: Message.Type,
        override val serverTimestamp: LocalDateTime,
        val msgGuid: UUID
    ) : Header

    data class BodyImpl(
        val inPlayEvents: List<EventImpl>
    ) : Body

    data class EventImpl(
        override val fixtureId: Long,
        val fixture: FixtureImpl,
        val providers: List<ProviderImpl>
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
        override val position: Int,
        override val rotationId: Long? = null,
        override val isActive: Boolean? = null,
        override val participantExtraData: ExtraData? = null
    ) : Participant

    data class ProviderImpl(
        override val id: Long,
        override val name: String,
        override val lastUpdate: LocalDateTime? = null,
        override val bets: List<Bet>? = null,
        override val providerFixtureId: String? = null,
        override val providerLeagueId: String? = null,
        override val providerMarketId: String? = null,
    ) : Provider
}
