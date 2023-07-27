package com.turbomates.kotlin.lsports.sdk.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Serializer(forClass = OffsetDateTime::class)
object OffsetDateTimeSerializer : KSerializer<OffsetDateTime> {
    private val dateTimeFormat: DateTimeFormatter
        get() = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS").withZone(ZoneOffset.UTC)

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("OffsetDateTime", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: OffsetDateTime) {
        encoder.encodeLong(value.toInstant().toEpochMilli())
    }

    @Suppress("MagicNumber")
    override fun deserialize(decoder: Decoder): OffsetDateTime {
        val dateTime = decoder.decodeString().let {
            when (it.length) {
                19 -> "$it.000000"
                25 -> "${it}0"
                26 -> it
                else -> {
                    var zeros = ""
                    (1..26 - it.length).forEach { _ -> zeros += "0" }
                    "$it$zeros"
                }
            }
        }

        return OffsetDateTime.parse(dateTime, dateTimeFormat)
    }
}
