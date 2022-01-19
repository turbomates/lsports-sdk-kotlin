package com.turbomates.kotlin.lsports.sdk.model.response

import com.turbomates.kotlin.lsports.sdk.model.Competition
import com.turbomates.kotlin.lsports.sdk.model.Event
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.OutrightFixture
import com.turbomates.kotlin.lsports.sdk.model.Response
import java.time.LocalDateTime

data class OutrightFixturesResponse(
    override val header: HeaderImpl,
    val body: List<OuterCompetitionImpl>
) : Response {
    data class HeaderImpl(
        override val type: Message.Type,
        override val serverTimestamp: LocalDateTime
    ) : Header

    data class OuterCompetitionImpl(
        override val id: Long,
        override val name: String,
        override val type: Competition.Type,
        override val competitions: List<InnerCompetitionImpl>,
        override val events: List<EventImpl>? = null
    ) : Competition

    data class InnerCompetitionImpl(
        override val id: Long,
        override val name: String,
        override val type: Competition.Type,
        override val competitions: List<InnerCompetitionImpl>? = null,
        override val events: List<EventImpl>
    ) : Competition

    data class EventImpl(
        override val fixtureId: Long,
        val outrightFixture: OutrightFixture
    ) : Event
}
