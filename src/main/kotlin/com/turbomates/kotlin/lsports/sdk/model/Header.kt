package com.turbomates.kotlin.lsports.sdk.model

import java.time.LocalDateTime

interface Header {
    val type: Message.Type
    val serverTimestamp: LocalDateTime
}
