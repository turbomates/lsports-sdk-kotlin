@file:UseSerializers(OffsetDateTimeSerializer::class)

package com.turbomates.kotlin.lsports.sdk.listener.message

import com.turbomates.kotlin.lsports.sdk.model.Bet.Settlement
import com.turbomates.kotlin.lsports.sdk.model.Bet.Status
import com.turbomates.kotlin.lsports.sdk.model.BetSuspensionReason
import com.turbomates.kotlin.lsports.sdk.model.Livescore
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
        val markets: List<Market<Bet>>,
        @SerialName("Livescore")
        val livescore: Livescore? = null
    )

    @Serializable
    data class Bet(
        @SerialName("Id")
        override val id: Long,
        @SerialName("Name")
        override val name: String,
        @SerialName("Line")
        val line: String? = null,
        @SerialName("BaseLine")
        val baseLine: String? = null,
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
        @SerialName("PlayerId")
        val playerId: Long? = null,
        @SerialName("SuspensionReason")
        val suspensionReason: BetSuspensionReason? = null,
        @SerialName("Settlement")
        val settlement: Settlement
    ) : com.turbomates.kotlin.lsports.sdk.model.Bet
}
