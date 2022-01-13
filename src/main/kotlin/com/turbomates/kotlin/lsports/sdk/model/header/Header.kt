package com.turbomates.kotlin.lsports.sdk.model.header

import com.turbomates.kotlin.lsports.sdk.model.message.Message
import java.time.LocalDateTime
import java.util.UUID

interface Header {
    val msgGuid: UUID
    val type: Message.Type
    val serverTimestamp: LocalDateTime
}
