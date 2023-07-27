package com.turbomates.kotlin.lsports.sdk.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

open class EnumWithValueSerializer<T : Enum<*>, TVal : Any>(
    serialName: String,
    private val valueSerializer: KSerializer<TVal>,
    val serializer: T.() -> TVal,
    val deserializer: (value: TVal) -> T,
) : KSerializer<T> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(serialName, valueSerializer.descriptor.kind as PrimitiveKind)

    override fun serialize(encoder: Encoder, value: T) {
        encoder.encodeSerializableValue(valueSerializer, serializer(value))
    }

    override fun deserialize(decoder: Decoder): T {
        val value = decoder.decodeSerializableValue(valueSerializer)
        return deserializer(value)
    }
}
