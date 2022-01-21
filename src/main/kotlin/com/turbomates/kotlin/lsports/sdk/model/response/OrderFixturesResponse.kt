package com.turbomates.kotlin.lsports.sdk.model.response

import com.turbomates.kotlin.lsports.sdk.model.Body
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.OrderFixture
import com.turbomates.kotlin.lsports.sdk.model.OrderFixtureError
import com.turbomates.kotlin.lsports.sdk.model.Response
import com.turbomates.kotlin.lsports.sdk.serializer.TimestampSerializer
import com.turbomates.kotlin.lsports.sdk.serializer.UUIDSerializer
import kotlinx.datetime.LocalDateTime
import java.util.UUID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderFixturesResponse(
    @SerialName("Header")
    override val header: HeaderImpl,
    @SerialName("Body")
    val body: BodyImpl
) : Response {
    @Serializable
    data class HeaderImpl(
        @SerialName("Type")
        override val type: Message.Type,
        @SerialName("ServerTimestamp")
        @Serializable(with = TimestampSerializer::class)
        override val serverTimestamp: LocalDateTime,
        @SerialName("MsgGuid")
        @Serializable(with = UUIDSerializer::class)
        val msgGuid: UUID
    ) : Header

    @Serializable
    data class BodyImpl(
        @SerialName("Ordered")
        val ordered: List<OrderFixture>? = null,
        @SerialName("Errors")
        val errors: List<OrderFixtureError>? = null
    ) : Body
}
