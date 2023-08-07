package com.turbomates.kotlin.lsports.sdk.model

import com.turbomates.kotlin.lsports.sdk.serializer.EnumWithValueSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

@Serializable
data class Subscription(
    @SerialName("Type")
    val type: Type,
    @SerialName("Status")
    val status: Status
) {
    @Serializable(with = TypeSerializer::class)
    enum class Type(val value: Int) {
        IN_PLAY(1),
        PRE_MATCH(2)
    }

    @Serializable(with = StatusSerializer::class)
    enum class Status(val value: Int) {
        SUBSCRIBED(1),
        PENDING(2),
        UNSUBSCRIBED(3),
        DELETED(4)
    }

    private class TypeSerializer : EnumWithValueSerializer<Type, Int>(
        "SubscriptionType",
        Int.serializer(),
        { value },
        { v ->  Type.values().first { it.value == v } }
    )

    private class StatusSerializer : EnumWithValueSerializer<Status, Int>(
        "SubscriptionStatus",
        Int.serializer(),
        { value },
        { v ->  Status.values().first { it.value == v } }
    )
}
