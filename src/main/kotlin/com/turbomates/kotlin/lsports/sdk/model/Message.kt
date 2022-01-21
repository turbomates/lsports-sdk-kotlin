package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface Message {
    val header: Header

    @Serializable
    enum class Type(val value: Int) {
        @SerialName("0")
        FULL_EVENT(0),
        @SerialName("1")
        FIXTURE_UPDATE(1),
        @SerialName("2")
        LIVESCORE_UPDATE(2),
        @SerialName("3")
        MARKET_UPDATE(3),
        @SerialName("4")
        LEAGUES(4),
        @SerialName("5")
        SPORTS(5),
        @SerialName("6")
        LOCATIONS(6),
        @SerialName("7")
        MARKETS(7),
        @SerialName("8")
        BOOKMAKERS(8),
        @SerialName("9")
        SCHEDULE(9),
        @SerialName("11")
        ORDER_FIXTURES(11),
        @SerialName("12")
        CANCEL_FIXTURE_ORDERS(12),
        @SerialName("31")
        KEEP_ALIVE(31),
        @SerialName("32")
        HEARTBEAT(32),
        @SerialName("35")
        SETTLEMENTS(35),
        @SerialName("36")
        SNAPSHOT(36),
        @SerialName("37")
        OUTRIGHT_FIXTURES(37),
        @SerialName("38")
        OUTRIGHT_LEAGUES(38)
    }
}
