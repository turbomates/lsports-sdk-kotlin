@file:UseSerializers(UUIDSerializer::class, OffsetDateTimeSerializer::class)

package com.turbomates.kotlin.lsports.sdk.listener.message

import com.turbomates.kotlin.lsports.sdk.serializer.EnumWithValueSerializer
import com.turbomates.kotlin.lsports.sdk.serializer.OffsetDateTimeSerializer
import com.turbomates.kotlin.lsports.sdk.serializer.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.builtins.serializer
import java.time.OffsetDateTime
import java.util.UUID

interface Message {
    val header: Header

    @Serializable
    data class Header(
        @SerialName("Type")
        val type: Type,
        @SerialName("MsgGuid")
        val msgGuid: UUID,
        @SerialName("ServerTimestamp")
        val serverTimestamp: Long,
        @SerialName("MsgSeq")
        val msgSeq: Long,
        @SerialName("CreationDate")
        val creationDate: OffsetDateTime
    )

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
