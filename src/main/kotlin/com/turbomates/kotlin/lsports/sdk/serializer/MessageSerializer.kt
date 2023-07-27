// package com.turbomates.kotlin.lsports.sdk.serializer
//
// import com.turbomates.kotlin.lsports.sdk.api.message.Message
// import com.turbomates.kotlin.lsports.sdk._model.message.FixtureUpdateMessage
// import com.turbomates.kotlin.lsports.sdk._model.message.HeartbeatMessage
// import com.turbomates.kotlin.lsports.sdk._model.message.KeepAliveMessage
// import com.turbomates.kotlin.lsports.sdk._model.message.LivescoreUpdateMessage
// import com.turbomates.kotlin.lsports.sdk._model.message.MarketUpdateMessage
// import com.turbomates.kotlin.lsports.sdk._model.message.OutrightLeaguesMessage
// import com.turbomates.kotlin.lsports.sdk._model.message.SettlementMessage
// import java.lang.UnsupportedOperationException
// import kotlinx.serialization.DeserializationStrategy
// import kotlinx.serialization.json.JsonContentPolymorphicSerializer
// import kotlinx.serialization.json.JsonElement
// import kotlinx.serialization.json.jsonObject
// import kotlinx.serialization.json.jsonPrimitive
// import org.slf4j.LoggerFactory
//
// object MessageSerializer : JsonContentPolymorphicSerializer<Message>(Message::class) {
//     private val logger = LoggerFactory.getLogger(javaClass)
//
//     override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Message> {
//         val typeValue =  element.jsonObject["Header"]?.jsonObject?.get("Type")?.jsonPrimitive?.content?.toInt()
//             ?: throw IllegalStateException("Message must contains Type value")
//
//         return when (typeValue) {
//             Message.Type.FIXTURE_UPDATE.value -> FixtureUpdateMessage.serializer()
//             Message.Type.LIVESCORE_UPDATE.value -> LivescoreUpdateMessage.serializer()
//             Message.Type.MARKET_UPDATE.value -> MarketUpdateMessage.serializer()
//             Message.Type.KEEP_ALIVE.value -> KeepAliveMessage.serializer()
//             Message.Type.SETTLEMENTS.value -> SettlementMessage.serializer()
//             Message.Type.HEARTBEAT.value -> HeartbeatMessage.serializer()
//             else -> {
//                 logger.error("MessageSerializer error with message data: $element")
//                 throw UnsupportedOperationException("Cannot find message with type $typeValue")
//             }
//         }
//     }
// }
