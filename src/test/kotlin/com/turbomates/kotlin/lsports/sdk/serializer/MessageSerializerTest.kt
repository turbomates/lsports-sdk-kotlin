package com.turbomates.kotlin.lsports.sdk.serializer

import com.turbomates.kotlin.lsports.sdk.listener.message.FixtureUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.HeartbeatMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.KeepAliveMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.LivescoreUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.MarketUpdateMessage
import com.turbomates.kotlin.lsports.sdk.listener.message.Message
import com.turbomates.kotlin.lsports.sdk.listener.message.SettlementsMessage
import com.turbomates.kotlin.lsports.sdk.model.BetSettlement
import com.turbomates.kotlin.lsports.sdk.model.Fixture
import com.turbomates.kotlin.lsports.sdk.model.Scoreboard
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.UUID
import kotlin.test.assertEquals

class MessageSerializerTest {
    @Test
    fun `fixture update message deserialization`() {
        val incomeData =
            "{ \"Header\": { \"Type\": 1, \"MsgSeq\": 2, \"MsgGuid\": \"564d10fb-c052-42b5-a288-9e8f935d9777\", \"CreationDate\": \"2023-08-03T07:53:45.4418488Z\", \"ServerTimestamp\": 1691049225443 }, \"Body\": { \"Events\": [ { \"FixtureId\": 11113180, \"Fixture\": { \"Subscription\": { \"Type\": 2, \"Status\": 1 }, \"Sport\": { \"Id\": 6046, \"Name\": \"Football\" }, \"Location\": { \"Id\": 248, \"Name\": \"International\" }, \"League\": { \"Id\": 54289, \"Name\": \"FIFA 23 Volta Daily League (E)\" }, \"StartDate\": \"2023-08-03T07:50:34Z\", \"LastUpdate\": \"2023-08-03T07:53:43.476279Z\", \"Status\": 9, \"Participants\": [ { \"Id\": 53008994, \"Name\": \"AC Milan (james)\", \"Position\": \"1\" }, { \"Id\": 53332588, \"Name\": \"arsenal (taroo)\", \"Position\": \"2\" } ], \"FixtureExtraData\": [ { \"Name\": \"WithInPlay\", \"Value\": \"true\" }, { \"Name\": \"WithLivescore\", \"Value\": \"true\" } ] }, \"Livescore\": null, \"Markets\": null } ] } }"
        val message = Json.decodeFromString(MessageSerializer, incomeData)

        Assertions.assertTrue(message is FixtureUpdateMessage)
        val fixtureUpdateMessage = message as FixtureUpdateMessage
        assertEquals(Message.Type.FIXTURE_UPDATE, fixtureUpdateMessage.header.type)
        assertEquals(UUID.fromString("564d10fb-c052-42b5-a288-9e8f935d9777"), fixtureUpdateMessage.header.msgGuid)
        assertEquals(11113180, fixtureUpdateMessage.body.events.first().fixtureId)
        assertEquals(53008994, fixtureUpdateMessage.body.events.first().fixture.participants.first().id)
    }

    @Test
    fun `livescore update message deserialization`() {
        val incomeData =
            "{ \"Header\": { \"Type\": 2, \"MsgSeq\": 1, \"MsgGuid\": \"308f58a0-f6a1-488c-88bb-41d4f9fed01b\", \"CreationDate\": \"2023-08-03T07:54:42.1124647Z\", \"ServerTimestamp\": 1691049282112 }, \"Body\": { \"Events\": [ { \"FixtureId\": 11112583, \"Fixture\": { \"Subscription\": null, \"Sport\": { \"Id\": 6046, \"Name\": \"Football\" }, \"Location\": null, \"League\": null, \"LastUpdate\": \"2023-08-03T07:50:13.311845Z\", \"Participants\": [ { \"Id\": 52325849, \"Name\": \"Al Zawra'a\", \"Position\": \"1\" }, { \"Id\": 53520637, \"Name\": \"Al-Muharaq\", \"Position\": \"2\" } ] }, \"Livescore\": { \"Scoreboard\": { \"Status\": 3, \"CurrentPeriod\": 100, \"Time\": \"5400\", \"Results\": [ { \"Position\": \"1\", \"Value\": \"2\" }, { \"Position\": \"2\", \"Value\": \"4\" } ] }, \"Periods\": [ { \"Type\": 100, \"IsFinished\": true, \"IsConfirmed\": false, \"Results\": [ { \"Position\": \"1\", \"Value\": \"2\" }, { \"Position\": \"2\", \"Value\": \"4\" } ], \"Incidents\": null, \"SubPeriods\": null, \"SequenceNumber\": 0 }, { \"Type\": 10, \"IsFinished\": true, \"IsConfirmed\": true, \"Results\": [ { \"Position\": \"1\", \"Value\": \"1\" }, { \"Position\": \"2\", \"Value\": \"2\" } ], \"Incidents\": [ { \"Period\": 10, \"IncidentType\": 27, \"Seconds\": 900, \"ParticipantPosition\": \"2\", \"Results\": [ { \"Position\": \"1\", \"Value\": \"0\" }, { \"Position\": \"2\", \"Value\": \"1\" } ] }, { \"Period\": 10, \"IncidentType\": 27, \"Seconds\": 1560, \"ParticipantPosition\": \"2\", \"Results\": [ { \"Position\": \"1\", \"Value\": \"0\" }, { \"Position\": \"2\", \"Value\": \"2\" } ] }, { \"Period\": 10, \"IncidentType\": 27, \"Seconds\": 1860, \"ParticipantPosition\": \"1\", \"Results\": [ { \"Position\": \"1\", \"Value\": \"1\" }, { \"Position\": \"2\", \"Value\": \"2\" } ] } ], \"SubPeriods\": null, \"SequenceNumber\": 0 }, { \"Type\": 20, \"IsFinished\": true, \"IsConfirmed\": true, \"Results\": [ { \"Position\": \"1\", \"Value\": \"1\" }, { \"Position\": \"2\", \"Value\": \"2\" } ], \"Incidents\": [ { \"Period\": 20, \"IncidentType\": 27, \"Seconds\": 3120, \"ParticipantPosition\": \"1\", \"Results\": [ { \"Position\": \"1\", \"Value\": \"2\" }, { \"Position\": \"2\", \"Value\": \"2\" } ] }, { \"Period\": 20, \"IncidentType\": 27, \"Seconds\": 3780, \"ParticipantPosition\": \"2\", \"Results\": [ { \"Position\": \"1\", \"Value\": \"2\" }, { \"Position\": \"2\", \"Value\": \"3\" } ] }, { \"Period\": 20, \"IncidentType\": 27, \"Seconds\": 4680, \"ParticipantPosition\": \"2\", \"Results\": [ { \"Position\": \"1\", \"Value\": \"2\" }, { \"Position\": \"2\", \"Value\": \"4\" } ] } ], \"SubPeriods\": null, \"SequenceNumber\": 0 } ], \"LivescoreExtraData\": [] }, \"Markets\": null } ] } }"
        val message = Json.decodeFromString(MessageSerializer, incomeData)

        Assertions.assertTrue(message is LivescoreUpdateMessage)
        val livescoreUpdateMessage = message as LivescoreUpdateMessage
        assertEquals(Message.Type.LIVESCORE_UPDATE, livescoreUpdateMessage.header.type)
        assertEquals(UUID.fromString("308f58a0-f6a1-488c-88bb-41d4f9fed01b"), livescoreUpdateMessage.header.msgGuid)
        assertEquals(11112583, livescoreUpdateMessage.body.events.first().fixtureId)
        assertEquals(Fixture.Status.FINISHED, livescoreUpdateMessage.body.events.first().livescore.scoreboard.status)
    }

    @Test
    fun `market update message deserialization`() {
        val incomeData =
            "{ \"Header\": { \"Type\": 3, \"MsgSeq\": 16, \"MsgGuid\": \"742798cb-a7ec-4212-9a3a-06f02b040ccb\", \"CreationDate\": \"2023-08-07T10:15:34.6751577Z\", \"ServerTimestamp\": 1691403334675 }, \"Body\": { \"Events\": [ { \"FixtureId\": 11098946, \"Livescore\": null, \"Markets\": [ { \"Id\": 712, \"Name\": \"First Player To Score\", \"Bets\": [ { \"PlayerId\": \"646189\", \"Id\": 149383821811098946, \"Name\": \"Kenny Ximenes\", \"Status\": 1, \"StartPrice\": \"1.0\", \"Price\": \"19\", \"ProviderBetId\": \"13\", \"LastUpdate\": \"2023-08-07T10:15:34.6747638Z\", \"ParticipantId\": 130237, \"PlayerName\": \"Kenny Ximenes\" } ] } ] } ] } }"
        val message = Json.decodeFromString(MessageSerializer, incomeData)

        Assertions.assertTrue(message is MarketUpdateMessage)
        val marketUpdateMessage = message as MarketUpdateMessage
        assertEquals(Message.Type.MARKET_UPDATE, marketUpdateMessage.header.type)
        assertEquals(UUID.fromString("742798cb-a7ec-4212-9a3a-06f02b040ccb"), marketUpdateMessage.header.msgGuid)
        assertEquals(11098946, marketUpdateMessage.body.events.first().fixtureId)
        assertEquals(
            1.0,
            marketUpdateMessage.body.events.first().markets.first().bets.first().startPrice
        )
    }

    @Test
    fun `keep alive message deserialization`() {
        val incomeData =
            "{\"Header\":{\"Type\":31,\"MsgGuid\":\"37a4cf5a-19a9-455c-b750-c408570fbcb9\",\"ServerTimestamp\":1642074018},\"Body\":{\"KeepAlive\":{\"ActiveEvent\":[8066615,8067007,8068049,8068502,8070309,8070443,8070487,8070593,8071084,8061953]}}}"
        val message = Json.decodeFromString(MessageSerializer, incomeData)

        Assertions.assertTrue(message is KeepAliveMessage)
        val keepAliveMessage = message as KeepAliveMessage
        assertEquals(Message.Type.KEEP_ALIVE, keepAliveMessage.header.type)
        assertEquals(UUID.fromString("37a4cf5a-19a9-455c-b750-c408570fbcb9"), keepAliveMessage.header.msgGuid)
        assertEquals(8066615, message.body.keepAlive.activeEvent.first())
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
            "{ \"Header\": { \"Type\": 35, \"MsgSeq\": 4, \"MsgGuid\": \"0e7fcfbe-798f-487b-9fca-274c05f18f88\", \"CreationDate\": \"2023-08-03T07:54:24.7916536Z\", \"ServerTimestamp\": 1691049264791 }, \"Body\": { \"Events\": [ { \"FixtureId\": 11092776, \"Livescore\": null, \"Markets\": [ { \"Id\": 9, \"Name\": \"Correct Score 1st Period\", \"Bets\": [ { \"Id\": 172814742111092776, \"Name\": \"1-4\", \"Status\": 3, \"StartPrice\": \"1.0\", \"Price\": \"17.0579\", \"Settlement\": 1, \"ProviderBetId\": \"145\", \"LastUpdate\": \"2023-08-03T07:54:24.7884546Z\" }, { \"Id\": 92157836711092776, \"Name\": \"1-2\", \"Status\": 3, \"StartPrice\": \"1.0\", \"Price\": \"17.0579\", \"Settlement\": 1, \"ProviderBetId\": \"145\", \"LastUpdate\": \"2023-08-03T07:54:24.7884546Z\" }, { \"Id\": 64450557411092776, \"Name\": \"1-3\", \"Status\": 3, \"StartPrice\": \"1.0\", \"Price\": \"17.0579\", \"Settlement\": 1, \"ProviderBetId\": \"145\", \"LastUpdate\": \"2023-08-03T07:54:24.7884546Z\" } ] } ] } ] } }"
        val message = Json.decodeFromString(MessageSerializer, incomeData)

        Assertions.assertTrue(message is SettlementsMessage)
        val settlementMessage = message as SettlementsMessage
        assertEquals(Message.Type.SETTLEMENTS, settlementMessage.header.type)
        assertEquals(UUID.fromString("0e7fcfbe-798f-487b-9fca-274c05f18f88"), settlementMessage.header.msgGuid)
        assertEquals(11092776, settlementMessage.body.events.first().fixtureId)
        assertEquals(
            BetSettlement.LOSER,
            settlementMessage.body.events.first().markets.first().bets.first().settlement
        )
    }
}
