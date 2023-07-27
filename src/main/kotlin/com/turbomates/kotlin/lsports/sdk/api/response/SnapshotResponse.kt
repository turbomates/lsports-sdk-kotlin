@file:UseSerializers(UUIDSerializer::class)

package com.turbomates.kotlin.lsports.sdk.api.response

import com.turbomates.kotlin.lsports.sdk.api.message.Message
import com.turbomates.kotlin.lsports.sdk.model.Fixture
import com.turbomates.kotlin.lsports.sdk.model.Livescore
import com.turbomates.kotlin.lsports.sdk.model.Market
import com.turbomates.kotlin.lsports.sdk.serializer.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.util.UUID

@Serializable
data class SnapshotResponse(
    @SerialName("Header")
    override val header: Header
) : Response {
    @SerialName("Body")
    private val _body: List<BodyItem> = listOf()

    override val body: Body
        get() = Body(_body)

    @Serializable
    data class Header(
        @SerialName("Type")
        val type: Message.Type,
        @SerialName("MsgGuid")
        val messageGuid: UUID,
        @SerialName("ServerTimestamp")
        val serverTimestamp: Long
    ) : Response.Header

    data class Body(val items: List<BodyItem>) : Response.Body

    @Serializable
    data class BodyItem(
        @SerialName("FixtureId")
        val fixtureId: Long,
        @SerialName("Fixture")
        val fixture: Fixture,
        @SerialName("Livescore")
        val livescore: Livescore? = null,
        @SerialName("Markets")
        val markets: List<Market>? = null
    )
}
