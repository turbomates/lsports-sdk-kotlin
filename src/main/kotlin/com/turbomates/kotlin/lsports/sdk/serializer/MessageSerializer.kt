package com.turbomates.kotlin.lsports.sdk.serializer

import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.message.FixtureUpdateMessage
import com.turbomates.kotlin.lsports.sdk.model.message.HeartbeatMessage
import com.turbomates.kotlin.lsports.sdk.model.message.KeepAliveMessage
import com.turbomates.kotlin.lsports.sdk.model.message.LivescoreUpdateMessage
import com.turbomates.kotlin.lsports.sdk.model.message.MarketUpdateMessage
import com.turbomates.kotlin.lsports.sdk.model.message.SettlementMessage
import java.lang.UnsupportedOperationException
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object MessageSerializer : JsonContentPolymorphicSerializer<Message>(Message::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Message> {
        val typeValue =  element.jsonObject["Header"]?.jsonObject?.get("Type")?.jsonPrimitive?.content?.toInt()
            ?: throw IllegalStateException("Message must contains Type value")

        return when (typeValue) {
            Message.Type.FIXTURE_UPDATE.value -> FixtureUpdateMessage.serializer()
            Message.Type.LIVESCORE_UPDATE.value -> LivescoreUpdateMessage.serializer()
            Message.Type.MARKET_UPDATE.value -> MarketUpdateMessage.serializer()
            Message.Type.KEEP_ALIVE.value -> KeepAliveMessage.serializer()
            Message.Type.SETTLEMENTS.value -> SettlementMessage.serializer()
            Message.Type.HEARTBEAT.value -> HeartbeatMessage.serializer()
            else -> throw UnsupportedOperationException("Cannot find message with type $typeValue")
        }
    }
}
