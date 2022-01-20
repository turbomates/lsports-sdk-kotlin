package com.turbomates.kotlin.lsports.sdk.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializer(forClass = Boolean::class)
object IntBooleanSerializer : KSerializer<Boolean> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("int_boolean", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): Boolean {
        return decoder.decodeInt() == 1
    }

    override fun serialize(encoder: Encoder, value: Boolean) {
        val intValue = if (value) 1 else 0
        encoder.encodeInt(intValue)
    }
}
