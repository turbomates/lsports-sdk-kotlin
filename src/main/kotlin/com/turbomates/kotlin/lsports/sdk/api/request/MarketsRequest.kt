package com.turbomates.kotlin.lsports.sdk.api.request

import com.turbomates.kotlin.lsports.sdk.serializer.EnumWithValueSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

@Serializable
data class MarketsRequest(
    @SerialName("SportsId")
    var sportIds: List<Long>? = null,
    @SerialName("LocationsId")
    var locationIds: List<Long>? = null,
    @SerialName("LeaguesId")
    var leagueIds: List<Long>? = null,
    @SerialName("MarketsId")
    var marketsId: List<Long>? = null,
    @SerialName("IsSettleable")
    var isSettleable: Boolean? = null,
    @SerialName("MarketType")
    var marketType: MarketType? = null
) : Request() {
    @Serializable(with = MarketTypeSerializer::class)
    enum class MarketType(val value: Int) {
        ALL(0),
        STANDARD_SPORT_MARKETS(1),
        OUTRIGHT_MARKETS(2)
    }

    private class MarketTypeSerializer : EnumWithValueSerializer<MarketType, Int>(
        "MarketsRequestMarketType",
        Int.serializer(),
        { value },
        { v -> MarketType.values().first { it.value == v } }
    )
}
