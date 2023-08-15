@file:UseSerializers(UUIDSerializer::class, OffsetDateTimeSerializer::class)

package com.turbomates.kotlin.lsports.sdk.api.response

import com.turbomates.kotlin.lsports.sdk.listener.message.Message
import com.turbomates.kotlin.lsports.sdk.model.Bet.Settlement
import com.turbomates.kotlin.lsports.sdk.model.Bet.Status
import com.turbomates.kotlin.lsports.sdk.model.BetSuspensionReason
import com.turbomates.kotlin.lsports.sdk.model.Fixture
import com.turbomates.kotlin.lsports.sdk.model.Livescore
import com.turbomates.kotlin.lsports.sdk.model.Market
import com.turbomates.kotlin.lsports.sdk.serializer.OffsetDateTimeSerializer
import com.turbomates.kotlin.lsports.sdk.serializer.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.OffsetDateTime
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
        val markets: List<Market<Bet>>? = null
    )

    @Serializable
    data class Bet(
        @SerialName("Id")
        override val id: Long,
        @SerialName("Name")
        override val name: String,
        @SerialName("Line")
        val line: String,
        @SerialName("BaseLine")
        val baseLine: String,
        @SerialName("Status")
        override val status: Status,
        @SerialName("StartPrice")
        override val startPrice: Double,
        @SerialName("Price")
        override val price: Double,
        @SerialName("LayPrice")
        val layPrice: Double? = null,
        @SerialName("PriceVolume")
        val priceVolume: Double? = null,
        @SerialName("LayPriceVolume")
        val layPriceVolume: Double? = null,
        @SerialName("ProviderBetId")
        val providerBetId: String? = null,
        @SerialName("LastUpdate")
        val lastUpdate: OffsetDateTime,
        @SerialName("ParticipantId")
        val participantId: Long? = null,
        @SerialName("Probability")
        val probability: Double? = null,
        @SerialName("PlayerName")
        val playerName: String? = null,
        @SerialName("SuspensionReason")
        val suspensionReason: BetSuspensionReason? = null,
        @SerialName("Settlement")
        val settlement: Settlement? = null
    ) : com.turbomates.kotlin.lsports.sdk.model.Bet
}
