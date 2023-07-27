package com.turbomates.kotlin.lsports.sdk.api.message

import com.turbomates.kotlin.lsports.sdk._model.Header
import com.turbomates.kotlin.lsports.sdk.serializer.EnumWithValueSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

interface Message {
    val header: Header

    @Serializable(with = TypeSerializer::class)
    enum class Type(val value: Int) {
        FIXTURE_UPDATE(1),
        LIVESCORE_UPDATE(2),
        MARKET_UPDATE(3),
        KEEP_ALIVE(31),
        HEARTBEAT(32),
        SETTLEMENTS(35),
        SNAPSHOT(36),
        OUTRIGHT_FIXTURE(37),
        OUTRIGHT_LEAGUE(38),
        OUTRIGHT_SCORE(39),
        OUTRIGHT_LEAGUE_MARKET(40),
        OUTRIGHT_FIXTURE_MARKET(41),
        OUTRIGHT_SETTLEMENTS(42);
    }

    private class TypeSerializer : EnumWithValueSerializer<Type, Int>(
        "MessageType",
        Int.serializer(),
        { value },
        { v -> Type.values().first { it.value == v } }
    )
}
