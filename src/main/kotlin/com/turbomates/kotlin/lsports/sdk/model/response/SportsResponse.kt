package com.turbomates.kotlin.lsports.sdk.model.response

import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Response
import com.turbomates.kotlin.lsports.sdk.model.Sport
import java.time.LocalDateTime

data class SportsResponse(
    override val header: HeaderImpl,
    val body: List<Sport>
) : Response {
    data class HeaderImpl(
        override val type: Message.Type,
        override val serverTimestamp: LocalDateTime
    ) : Header
}
