@file:UseSerializers(OffsetDateTimeSerializer::class)

package com.turbomates.kotlin.lsports.sdk.listener.message

import com.turbomates.kotlin.lsports.sdk.model.BetSettlement
import com.turbomates.kotlin.lsports.sdk.model.BetStatus
import com.turbomates.kotlin.lsports.sdk.model.BetSuspensionReason
import com.turbomates.kotlin.lsports.sdk.model.Market
import com.turbomates.kotlin.lsports.sdk.serializer.OffsetDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.OffsetDateTime

@Serializable
data class SettlementsMessage(
    @SerialName("Header")
    override val header: Message.Header,
    @SerialName("Body")
    val body: Body
) : Message {
    @Serializable
    data class Body(
        @SerialName("Events")
        val events: List<Event>
    )

    @Serializable
    data class Event(
        @SerialName("FixtureId")
        val fixtureId: Long,
        @SerialName("Markets")
        val markets: List<Market<Bet>>
    )

    @Serializable
    data class Bet(
        @SerialName("Id")
        val id: Long,
        @SerialName("Name")
        val name: String,
        @SerialName("Line")
        val line: String? = null,
        @SerialName("BaseLine")
        val baseLine: String? = null,
        @SerialName("Status")
        val status: BetStatus,
        @SerialName("StartPrice")
        val startPrice: Double,
        @SerialName("Price")
        val price: Double,
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
        val settlement: BetSettlement
    )
}
