package com.turbomates.kotlin.lsports.sdk.serializer

import com.turbomates.kotlin.lsports.sdk.listener.message.FixtureUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.HeartbeatMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.KeepAliveMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.LivescoreUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.MarketUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.Message
import com.turbomates.kotlin.lsports.sdk.listener.message.SettlementsMessage
import java.lang.UnsupportedOperationException
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.slf4j.LoggerFactory

object MessageSerializer : JsonContentPolymorphicSerializer<Message>(Message::class) {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<Message> {
        val typeValue = element.jsonObject["Header"]?.jsonObject?.get("Type")?.jsonPrimitive?.content?.toInt()
            ?: error("Message must contains Type value")

        return when (typeValue) {
            Message.Type.FIXTURE_UPDATE.value -> FixtureUpdateMessage.serializer()
            Message.Type.LIVESCORE_UPDATE.value -> LivescoreUpdateMessage.serializer()
            Message.Type.MARKET_UPDATE.value -> MarketUpdateMessage.serializer()
            Message.Type.KEEP_ALIVE.value -> KeepAliveMessage.serializer()
            Message.Type.SETTLEMENTS.value -> SettlementsMessage.serializer()
            Message.Type.HEARTBEAT.value -> HeartbeatMessage.serializer()
            else -> {
                logger.error("MessageSerializer error with message data: $element")
                if (Message.Type.entries.map { it.value }.contains(typeValue)) {
                    throw UnimplementedMessageTypeException(Message.Type.entries.first { it.value == typeValue })
                } else {
                    throw UnsupportedMessageTypeException(typeValue)
                }
            }

        }
    }

    class UnsupportedMessageTypeException(type: Int? = null) :
        Exception("MessageSerializer error: unsupported message type $type")

    class UnimplementedMessageTypeException(type: Message.Type) :
        Exception("MessageSerializer error: serializer doesn't implemented for type $type")
}
