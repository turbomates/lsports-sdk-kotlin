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
        return OffsetDateTime.parse(decoder.decodeString())
        // val dateTime = decoder.decodeString().let {
        //     val time = it.replace("Z", "0")
        //     when (it.length) {
        //         19 -> "$time.000000"
        //         26 -> time
        //         else -> if (time.length > 26) time.dropLast(time.length - 26) else {
        //             var zeros = ""
        //             (1..26 - time.length).forEach { _ -> zeros += "0" }
        //             "$time$zeros"
        //         }
        //     }
        // }

        // return OffsetDateTime.parse(dateTime, dateTimeFormat)
    }
}
