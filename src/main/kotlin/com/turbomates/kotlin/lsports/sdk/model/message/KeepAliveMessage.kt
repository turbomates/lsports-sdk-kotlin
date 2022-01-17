package com.turbomates.kotlin.lsports.sdk.model.message

import com.turbomates.kotlin.lsports.sdk.model.Body
import com.turbomates.kotlin.lsports.sdk.model.KeepAlive
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Header
import java.time.LocalDateTime
import java.util.UUID

data class KeepAliveMessage(
    override val header: HeaderImpl,
    val body: BodyImpl
) : Message {
    data class HeaderImpl(
        override val type: Message.Type,
        override val serverTimestamp: LocalDateTime,
        val msgGuid: UUID,
    ) : Header

    data class BodyImpl(
        val keepAlive: KeepAlive
    ) : Body
}
