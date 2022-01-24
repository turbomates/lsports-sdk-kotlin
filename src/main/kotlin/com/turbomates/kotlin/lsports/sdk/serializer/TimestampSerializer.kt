package com.turbomates.kotlin.lsports.sdk.serializer

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset.UTC
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializer(forClass = LocalDateTime::class)
object TimestampSerializer : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("uuid", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): LocalDateTime {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(decoder.decodeLong()), UTC)
    }

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        encoder.encodeLong(value.toInstant(UTC).toEpochMilli())
    }
}
