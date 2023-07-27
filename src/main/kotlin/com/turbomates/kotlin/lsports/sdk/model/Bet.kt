@file:UseSerializers(OffsetDateTimeSerializer::class)

package com.turbomates.kotlin.lsports.sdk.model

import com.turbomates.kotlin.lsports.sdk.serializer.EnumWithValueSerializer
import com.turbomates.kotlin.lsports.sdk.serializer.OffsetDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.builtins.serializer
import java.time.OffsetDateTime

@Serializable
data class Bet(
    @SerialName("Id")
    val id: Long,
    @SerialName("Name")
    val name: String,
    @SerialName("Line")
    val line: String,
    @SerialName("BaseLine")
    val baseLine: String,
    @SerialName("Status")
    val status: Status,
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
    @SerialName("Settlement")
    val settlement: Settlement? = null,
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
    val suspensionReason: SuspensionReason? = null
) {
    @Serializable(with = StatusSerializer::class)
    enum class Status(val value: Int) {
        OPEN(1),
        SUSPENDED(2),
        SETTLED(3)
    }

    @Serializable(with = SettlementSerializer::class)
    enum class Settlement(val value: Int) {
        CANCELLED(-1),
        LOSER(1),
        WINNER(2),
        REFUND(3),
        HALF_LOST(4),
        HALF_WON(5)
    }

    @Serializable(with = SuspensionReasonSerializer::class)
    enum class SuspensionReason(val value: Int) {
        PROVIDERS(1),
        MAJORITY(3),
        THRESHOLD(4),
        MANDATORY_PROVIDERS(5),
        LIVESCORE_INCONSISTENCY(6),
        OUT_OF_PRICE_RANGE(7),
        LINE_TYPE_REMOVAL(8),
        OUT_OF_LINE_RANGE(9),
        FIXTURE_STATUS(10),
        MAIN_LINE_ONLY(11),
        DATA_LIMIT(12),
        OUTLIER(13),
        SETTINGS_CHANGE(14),
        FAIR_PRICE_CALCULATION(15),
        NEGATIVE_MARGIN(16)
    }

    private class StatusSerializer : EnumWithValueSerializer<Status, Int>(
        "BetStatus",
        Int.serializer(),
        { value },
        { v -> Status.values().first { it.value == v } }
    )

    private class SettlementSerializer : EnumWithValueSerializer<Settlement, Int>(
        "BetSettlement",
        Int.serializer(),
        { value },
        { v -> Settlement.values().first { it.value == v } }
    )

    private class SuspensionReasonSerializer : EnumWithValueSerializer<SuspensionReason, Int>(
        "BetSuspensionReason",
        Int.serializer(),
        { value },
        { v -> SuspensionReason.values().first { it.value == v } }
    )
}
