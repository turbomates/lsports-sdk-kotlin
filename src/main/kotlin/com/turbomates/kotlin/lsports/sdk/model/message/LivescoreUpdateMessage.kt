package com.turbomates.kotlin.lsports.sdk.model.message

import com.turbomates.kotlin.lsports.sdk.model.Body
import com.turbomates.kotlin.lsports.sdk.model.Event
import com.turbomates.kotlin.lsports.sdk.model.Livescore
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Header
import java.time.LocalDateTime
import java.util.UUID

data class LivescoreUpdateMessage(
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
        val livescore: Livescore
    ) : Event
}
