package com.turbomates.kotlin.lsports.sdk.model.response

import com.turbomates.kotlin.lsports.sdk.model.Location
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.Response
import java.time.LocalDateTime

data class LocationsResponse(
    override val header: HeaderImpl,
    val body: List<Location>
) : Response {
    data class HeaderImpl(
        override val type: Message.Type,
        override val serverTimestamp: LocalDateTime
    ) : Header
}
