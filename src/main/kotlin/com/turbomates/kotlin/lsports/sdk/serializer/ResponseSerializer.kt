package com.turbomates.kotlin.lsports.sdk.serializer

import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Response
import com.turbomates.kotlin.lsports.sdk.model.response.FullEventResponse
import java.lang.UnsupportedOperationException
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object ResponseSerializer : JsonContentPolymorphicSerializer<Response>(Response::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Response> {
        val typeValue =  element.jsonObject["Header"]?.jsonObject?.get("Type")?.jsonPrimitive?.content?.toInt()
            ?: throw IllegalStateException("Response must contains Type value")

        return when (typeValue) {
            Message.Type.FULL_EVENT.value -> FullEventResponse.serializer()
            else -> throw UnsupportedOperationException("Cannot find response with type $typeValue")
        }
    }
}
