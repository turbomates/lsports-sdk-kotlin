package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.datetime.LocalDateTime

interface Header {
    val type: Message.Type
    val serverTimestamp: LocalDateTime
}
