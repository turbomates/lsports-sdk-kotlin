package com.turbomates.kotlin.lsports.sdk.model.message

import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Header
import java.time.LocalDateTime
import java.util.UUID

data class HeartbeatMessage(
    override val header: HeaderImpl
) : Message {
    data class HeaderImpl(
        override val type: Message.Type,
        override val serverTimestamp: LocalDateTime,
        val msgGuid: UUID,
    ) : Header
}
