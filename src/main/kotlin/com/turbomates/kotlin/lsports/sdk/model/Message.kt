package com.turbomates.kotlin.lsports.sdk.model

import java.time.LocalDateTime
import java.util.UUID

data class Message(
    val header: Header,
    val body: Body
) {
    data class Header(
        val id: UUID,
        val type: Type,
        val sequence: Int,
        val timestamp: LocalDateTime
    )

    data class Body(
        val events: List<Any>,
        val keepAlive: Any
    )

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
