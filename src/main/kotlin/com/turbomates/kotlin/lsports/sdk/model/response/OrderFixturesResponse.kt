package com.turbomates.kotlin.lsports.sdk.model.response

import com.turbomates.kotlin.lsports.sdk.model.Body
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.OrderFixture
import com.turbomates.kotlin.lsports.sdk.model.OrderFixtureError
import com.turbomates.kotlin.lsports.sdk.model.Response
import java.time.LocalDateTime
import java.util.UUID

data class OrderFixturesResponse(
    override val header: HeaderImpl,
    val body: BodyImpl
) : Response {
    data class HeaderImpl(
        override val type: Message.Type,
        override val serverTimestamp: LocalDateTime,
        val msgGuid: UUID
    ) : Header

    data class BodyImpl(
        val ordered: List<OrderFixture>,
        val errors: List<OrderFixtureError>? = null
    ) : Body
}
