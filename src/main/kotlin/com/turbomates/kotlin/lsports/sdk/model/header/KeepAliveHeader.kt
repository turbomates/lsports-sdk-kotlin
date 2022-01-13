package com.turbomates.kotlin.lsports.sdk.model.header

import com.turbomates.kotlin.lsports.sdk.model.message.Message
import java.time.LocalDateTime
import java.util.UUID

data class KeepAliveHeader(
    override val msgGuid: UUID,
    override val type: Message.Type,
    override val serverTimestamp: LocalDateTime
) : Header
