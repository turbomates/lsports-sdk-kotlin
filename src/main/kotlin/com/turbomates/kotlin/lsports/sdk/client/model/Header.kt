package com.turbomates.kotlin.lsports.sdk.client.model

import java.time.LocalDateTime
import java.util.UUID

data class Header(
    val id: UUID,
    val type: Message.Type,
    val sequence: Int,
    val timestamp: LocalDateTime
)
