package com.turbomates.kotlin.lsports.sdk.serializer

import com.turbomates.kotlin.lsports.sdk.model.Bet
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Scoreboard
import com.turbomates.kotlin.lsports.sdk.model.message.FixtureUpdateMessage
import com.turbomates.kotlin.lsports.sdk.model.message.HeartbeatMessage
import com.turbomates.kotlin.lsports.sdk.model.message.KeepAliveMessage
import com.turbomates.kotlin.lsports.sdk.model.message.LivescoreUpdateMessage
import com.turbomates.kotlin.lsports.sdk.model.message.MarketUpdateMessage
import com.turbomates.kotlin.lsports.sdk.model.message.SettlementMessage
import java.util.UUID
import kotlin.test.assertEquals
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MessageSerializerTest {
    @Test
    fun `fixture update message deserialization`() {
        val incomeData =
            "{\"Header\":{\"Type\":1,\"MsgId\":2,\"MsgGuid\":\"0e44cf1e-8194-4974-ae2b-ec1bf1a87f0d\",\"ServerTimestamp\":1641989952},\"Body\":{\"Events\":[{\"FixtureId\":8069324,\"Fixture\":{\"Sport\":{\"Id\":48242,\"Name\":\"Basketball\"},\"Location\":{\"Id\":248,\"Name\":\"International\"},\"League\":{\"Id\":43902,\"Name\":\"NBA Liga Pro 4x5mins (E)\"},\"StartDate\":\"2022-01-12T12:18:00\",\"LastUpdate\":\"2022-01-12T12:19:00.244965\",\"Status\":1,\"Participants\":[{\"Id\":52651363,\"Name\":\"San Antonio Spurs (ole-g420)\",\"Position\":\"1\"},{\"Id\":52601755,\"Name\":\"bossvlados0_0 esports\",\"Position\":\"2\"}]},\"Livescore\":null,\"Markets\":null}]}}"
        val message = Json.decodeFromString(MessageSerializer, incomeData)

        Assertions.assertTrue(message is FixtureUpdateMessage)
        val fixtureUpdateMessage = message as FixtureUpdateMessage
        assertEquals(Message.Type.FIXTURE_UPDATE, fixtureUpdateMessage.header.type)
        assertEquals(UUID.fromString("0e44cf1e-8194-4974-ae2b-ec1bf1a87f0d"), fixtureUpdateMessage.header.msgGuid)
        assertEquals(8069324, fixtureUpdateMessage.body.events.first().fixtureId)
        assertEquals(52651363, fixtureUpdateMessage.body.events.first().fixture.participants.first().id)
    }

    @Test
    fun `livescore update message deserialization`() {
        val incomeData =
            "{\"Header\":{\"Type\":2,\"MsgId\":1,\"MsgGuid\":\"0ba4f08c-4190-4a18-b6b7-c3088bbd7aa0\",\"ServerTimestamp\":1641989957},\"Body\":{\"Events\":[{\"FixtureId\":8067540,\"Livescore\":{\"Scoreboard\":{\"Status\":3,\"CurrentPeriod\":100,\"Time\":\"5400\",\"Results\":[{\"Position\":\"1\",\"Value\":\"1\"},{\"Position\":\"2\",\"Value\":\"2\"}]},\"Periods\":[{\"Type\":100,\"IsFinished\":true,\"IsConfirmed\":true,\"Results\":[{\"Position\":\"1\",\"Value\":\"1\"},{\"Position\":\"2\",\"Value\":\"2\"}],\"Incidents\":null,\"SubPeriods\":null,\"SequenceNumber\":0},{\"Type\":10,\"IsFinished\":true,\"IsConfirmed\":true,\"Results\":[{\"Position\":\"1\",\"Value\":\"0\"},{\"Position\":\"2\",\"Value\":\"1\"}],\"Incidents\":[{\"Period\":10,\"IncidentType\":1,\"Seconds\":0,\"ParticipantPosition\":\"0\",\"Results\":[{\"Position\":\"1\",\"Value\":\"0\"},{\"Position\":\"2\",\"Value\":\"1\"}]}],\"SubPeriods\":null,\"SequenceNumber\":0},{\"Type\":20,\"IsFinished\":true,\"IsConfirmed\":true,\"Results\":[{\"Position\":\"1\",\"Value\":\"1\"},{\"Position\":\"2\",\"Value\":\"1\"}],\"Incidents\":[{\"Period\":20,\"IncidentType\":1,\"Seconds\":0,\"ParticipantPosition\":\"0\",\"Results\":[{\"Position\":\"1\",\"Value\":\"1\"},{\"Position\":\"2\",\"Value\":\"2\"}]}],\"SubPeriods\":null,\"SequenceNumber\":0}],\"LivescoreExtraData\":[]},\"Markets\":null}]}}"
        val message = Json.decodeFromString(MessageSerializer, incomeData)

        Assertions.assertTrue(message is LivescoreUpdateMessage)
        val livescoreUpdateMessage = message as LivescoreUpdateMessage
        assertEquals(Message.Type.LIVESCORE_UPDATE, livescoreUpdateMessage.header.type)
        assertEquals(UUID.fromString("0ba4f08c-4190-4a18-b6b7-c3088bbd7aa0"), livescoreUpdateMessage.header.msgGuid)
        assertEquals(8067540, livescoreUpdateMessage.body.events.first().fixtureId)
        assertEquals(Scoreboard.Status.FINISHED, livescoreUpdateMessage.body.events.first().livescore.scoreboard.status)
    }

    @Test
    fun `market update message deserialization`() {
        val incomeData =
            "{\"Header\":{\"Type\":3,\"MsgId\":0,\"MsgGuid\":\"4e0e2955-6340-4926-9799-064a98cb1766\",\"ServerTimestamp\":1641989967},\"Body\":{\"Events\":[{\"FixtureId\":8061863,\"Livescore\":null,\"Markets\":[{\"Id\":226,\"Name\":\"12 Including Overtime\",\"Providers\":[{\"Id\":13,\"Name\":\"BWin\",\"LastUpdate\":\"2022-01-12T12:19:27.8315406Z\",\"Bets\":[{\"Id\":11639096768061863,\"Name\":\"1\",\"Status\":1,\"StartPrice\":\"2.15\",\"Price\":\"2.5\",\"LastUpdate\":\"2022-01-12T12:19:27.8315406Z\"},{\"Id\":15649736798061863,\"Name\":\"2\",\"Status\":1,\"StartPrice\":\"1.65\",\"Price\":\"1.5\",\"LastUpdate\":\"2022-01-12T12:19:27.8315406Z\"}]}]}]}]}}"
        val message = Json.decodeFromString(MessageSerializer, incomeData)

        Assertions.assertTrue(message is MarketUpdateMessage)
        val marketUpdateMessage = message as MarketUpdateMessage
        assertEquals(Message.Type.MARKET_UPDATE, marketUpdateMessage.header.type)
        assertEquals(UUID.fromString("4e0e2955-6340-4926-9799-064a98cb1766"), marketUpdateMessage.header.msgGuid)
        assertEquals(8061863, marketUpdateMessage.body.events.first().fixtureId)
        assertEquals(2.15, marketUpdateMessage.body.events.first().markets.first().providers.first().bets.first().startPrice)
    }

    @Test
    fun `keep alive message deserialization`() {
        val incomeData =
            "{\"Header\":{\"Type\":31,\"MsgGuid\":\"37a4cf5a-19a9-455c-b750-c408570fbcb9\",\"ServerTimestamp\":1642074018},\"Body\":{\"KeepAlive\":{\"ActiveEvents\":[8066615,8067007,8068049,8068502,8070309,8070443,8070487,8070593,8071084,8061953],\"ExtraData\":null,\"ProviderId\":75}}}"
        val message = Json.decodeFromString(MessageSerializer, incomeData)

        Assertions.assertTrue(message is KeepAliveMessage)
        val keepAliveMessage = message as KeepAliveMessage
        assertEquals(Message.Type.KEEP_ALIVE, keepAliveMessage.header.type)
        assertEquals(UUID.fromString("37a4cf5a-19a9-455c-b750-c408570fbcb9"), keepAliveMessage.header.msgGuid)
        assertEquals(8066615, message.body.keepAlive.activeEvents.first())
    }

    @Test
    fun `heartbeat message deserialization`() {
        val incomeData =
            "{\"Header\":{\"Type\":32,\"MsgGuid\":\"eab44cc7-8070-4f39-9a6d-3ccf10916dc5\",\"ServerTimestamp\":1641989939}}"
        val message = Json.decodeFromString(MessageSerializer, incomeData)

        Assertions.assertTrue(message is HeartbeatMessage)
        val heartbeatMessage = message as HeartbeatMessage
        assertEquals(Message.Type.HEARTBEAT, heartbeatMessage.header.type)
        assertEquals(UUID.fromString("eab44cc7-8070-4f39-9a6d-3ccf10916dc5"), heartbeatMessage.header.msgGuid)
    }

    @Test
    fun `settlement message deserialization`() {
        val incomeData =
            "{\"Header\":{\"Type\":35,\"MsgId\":1,\"MsgGuid\":\"ca4da570-a87d-4e06-9d68-770b229a2df4\",\"ServerTimestamp\":1641989955},\"Body\":{\"Events\":[{\"FixtureId\":8066430,\"Livescore\":null,\"Markets\":[{\"Id\":9,\"Name\":\"Correct Score 1st Period\",\"Providers\":[{\"Id\":145,\"Name\":\"1XBet\",\"LastUpdate\":\"2022-01-12T12:19:15.8408925Z\",\"Bets\":[{\"Id\":13604693718066430,\"Name\":\"6-2\",\"Status\":3,\"StartPrice\":\"6.0\",\"Price\":\"5.5\",\"Settlement\":1,\"LastUpdate\":\"2022-01-12T12:19:15.837427Z\"}]}]}]}]}}\n"
        val message = Json.decodeFromString(MessageSerializer, incomeData)

        Assertions.assertTrue(message is SettlementMessage)
        val settlementMessage = message as SettlementMessage
        assertEquals(Message.Type.SETTLEMENTS, settlementMessage.header.type)
        assertEquals(UUID.fromString("ca4da570-a87d-4e06-9d68-770b229a2df4"), settlementMessage.header.msgGuid)
        assertEquals(8066430, settlementMessage.body.events.first().fixtureId)
        assertEquals(Bet.Settlement.LOSER, settlementMessage.body.events.first().markets.first().providers.first().bets.first().settlement)
    }
}
