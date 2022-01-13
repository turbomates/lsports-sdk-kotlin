package com.turbomates.kotlin.lsports.sdk.model.message

import com.turbomates.kotlin.lsports.sdk.model.header.Header

interface Message {
    val header: Header

    enum class Type(val value: Int) {
        FULL_EVENT(0),
        FIXTURE_UPDATE(1),
        LIVESCORE_UPDATE(2),
        MARKET_UPDATE(3),
        LEAGUES(4),
        SPORTS(5),
        LOCATIONS(6),
        MARKETS(7),
        BOOKMAKERS(8),
        KEEP_ALIVE(31),
        HEARTBEAT(32),
        SETTLEMENTS(35),
        SNAPSHOT(36),
        OUTRIGHT_FIXTURE(37),
        OUTRIGHT_LEAGUE(38)
    }
}
