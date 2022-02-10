package com.turbomates.kotlin.lsports.sdk.serializer

import com.turbomates.kotlin.lsports.sdk.model.Bet
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Scoreboard
import com.turbomates.kotlin.lsports.sdk.model.message.FixtureUpdateMessage
import com.turbomates.kotlin.lsports.sdk.model.message.HeartbeatMessage
import com.turbomates.kotlin.lsports.sdk.model.message.KeepAliveMessage
import com.turbomates.kotlin.lsports.sdk.model.message.LivescoreUpdateMessage
import com.turbomates.kotlin.lsports.sdk.model.message.MarketUpdateMessage
import com.turbomates.kotlin.lsports.sdk.model.message.OutrightLeaguesMessage
import com.turbomates.kotlin.lsports.sdk.model.message.SettlementMessage
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.UUID
import kotlin.test.assertEquals

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
        assertEquals(
            2.15,
            marketUpdateMessage.body.events.first().markets.first().providers.first().bets.first().startPrice
        )
    }

    @Test
    fun `market update message with settlement deserialization`() {
        val incomeData = "{\"Header\":{\"Type\":3,\"MsgId\":0,\"MsgGuid\":\"99498c28-c20b-4fb3-968c-ad614613e9a0\",\"ServerTimestamp\":1644451404},\"Body\":{\"Events\":[{\"FixtureId\":8090226,\"Livescore\":null,\"Markets\":[{\"Id\":711,\"Name\":\"Player To Score In Anytime\",\"Providers\":[{\"Id\":13,\"Name\":\"BWin\",\"LastUpdate\":\"2022-02-10T00:03:24.2576255Z\",\"Bets\":[{\"Id\":18821367248090226,\"Name\":\"Josh Brownhill\",\"Status\":2,\"StartPrice\":\"13.0\",\"Price\":\"13.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":14839634688090226,\"Name\":\"Aaron Wan-Bissaka\",\"Status\":2,\"StartPrice\":\"21.0\",\"Price\":\"21.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":8637939648090226,\"Name\":\"Jadon Sancho\",\"Status\":2,\"StartPrice\":\"3.6\",\"Price\":\"3.6\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":474546688090226,\"Name\":\"Diogo Dalot\",\"Status\":2,\"StartPrice\":\"13.0\",\"Price\":\"15.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":8949178128090226,\"Name\":\"Harry Maguire\",\"Status\":2,\"StartPrice\":\"10.0\",\"Price\":\"10.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":6126375148090226,\"Name\":\"Jay Rodriguez\",\"Status\":3,\"StartPrice\":\"3.6\",\"Price\":\"3.6\",\"Settlement\":2,\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":8549082128090226,\"Name\":\"Aaron Lennon\",\"Status\":2,\"StartPrice\":\"10.0\",\"Price\":\"10.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":8887532208090226,\"Name\":\"Ben Mee\",\"Status\":2,\"StartPrice\":\"13.0\",\"Price\":\"13.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":4999130048090226,\"Name\":\"Nemanja Matic\",\"Status\":2,\"StartPrice\":\"10.0\",\"Price\":\"10.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":9708760128090226,\"Name\":\"Phil Jones\",\"Status\":2,\"StartPrice\":\"21.0\",\"Price\":\"21.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":17101240128090226,\"Name\":\"Charlie Taylor\",\"Status\":2,\"StartPrice\":\"41.0\",\"Price\":\"51.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":15259715708090226,\"Name\":\"Alex Telles\",\"Status\":2,\"StartPrice\":\"15.0\",\"Price\":\"15.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":4058621888090226,\"Name\":\"Anthony Elanga\",\"Status\":2,\"StartPrice\":\"3.5\",\"Price\":\"3.75\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":15130363568090226,\"Name\":\"Luke Shaw\",\"Status\":2,\"StartPrice\":\"15.0\",\"Price\":\"15.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":13901035248090226,\"Name\":\"Marcus Rashford\",\"Status\":2,\"StartPrice\":\"2.4\",\"Price\":\"2.5\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":19349434628090226,\"Name\":\"Jesse Lingard\",\"Status\":2,\"StartPrice\":\"3.6\",\"Price\":\"3.6\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":5117082048090226,\"Name\":\"Kevin Long\",\"Status\":2,\"StartPrice\":\"26.0\",\"Price\":\"26.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":6174806608090226,\"Name\":\"Paul Pogba\",\"Status\":3,\"StartPrice\":\"4.5\",\"Price\":\"4.5\",\"Settlement\":2,\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":212638528090226,\"Name\":\"Fred\",\"Status\":2,\"StartPrice\":\"8.0\",\"Price\":\"8.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":11916140928090226,\"Name\":\"Bruno Fernandes\",\"Status\":2,\"StartPrice\":\"2.7\",\"Price\":\"2.7\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":3055597648090226,\"Name\":\"Edinson Cavani\",\"Status\":2,\"StartPrice\":\"2.4\",\"Price\":\"2.4\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":4948267328090226,\"Name\":\"Juan Manuel Mata\",\"Status\":2,\"StartPrice\":\"5.0\",\"Price\":\"5.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":16296230208090226,\"Name\":\"Dale Stephens\",\"Status\":2,\"StartPrice\":\"17.0\",\"Price\":\"19.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":5441462528090226,\"Name\":\"Owen Dodgson\",\"Status\":2,\"StartPrice\":\"12.0\",\"Price\":\"13.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":15227456128090226,\"Name\":\"Jack Cork\",\"Status\":2,\"StartPrice\":\"17.0\",\"Price\":\"19.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":6227799248090226,\"Name\":\"Connor Roberts\",\"Status\":2,\"StartPrice\":\"17.0\",\"Price\":\"17.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":16044223168090226,\"Name\":\"Eric Bailly\",\"Status\":2,\"StartPrice\":\"17.0\",\"Price\":\"17.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":19729684508090226,\"Name\":\"Cristiano Ronaldo\",\"Status\":2,\"StartPrice\":\"2.15\",\"Price\":\"2.15\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":18622036688090226,\"Name\":\"Barnes, Ashley\",\"Status\":2,\"StartPrice\":\"3.6\",\"Price\":\"3.6\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":7389092928090226,\"Name\":\"Nathan Collins\",\"Status\":2,\"StartPrice\":\"21.0\",\"Price\":\"23.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":5054197328090226,\"Name\":\"Bobby Thomas\",\"Status\":2,\"StartPrice\":\"34.0\",\"Price\":\"34.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":17312294308090226,\"Name\":\"James Tarkowski\",\"Status\":2,\"StartPrice\":\"21.0\",\"Price\":\"23.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":17765132608090226,\"Name\":\"Raphael Varane\",\"Status\":3,\"StartPrice\":\"15.0\",\"Price\":\"15.0\",\"Settlement\":-1,\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":8473875648090226,\"Name\":\"Matthew Lowton\",\"Status\":2,\"StartPrice\":\"29.0\",\"Price\":\"29.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":15216363248090226,\"Name\":\"Ashley Westwood\",\"Status\":2,\"StartPrice\":\"15.0\",\"Price\":\"15.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":17623513328090226,\"Name\":\"Victor Lindelof\",\"Status\":2,\"StartPrice\":\"17.0\",\"Price\":\"17.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":4987504128090226,\"Name\":\"Erik Pieters\",\"Status\":2,\"StartPrice\":\"26.0\",\"Price\":\"26.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":4247868928090226,\"Name\":\"Scott Mctominay\",\"Status\":2,\"StartPrice\":\"6.5\",\"Price\":\"6.5\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":13923626448090226,\"Name\":\"Wout Weghorst\",\"Status\":2,\"StartPrice\":\"3.3\",\"Price\":\"3.3\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":260142288090226,\"Name\":\"Bardsley, Phil\",\"Status\":2,\"StartPrice\":\"29.0\",\"Price\":\"34.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":18412377308090226,\"Name\":\"Maxwel Cornet\",\"Status\":2,\"StartPrice\":\"3.25\",\"Price\":\"3.3\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"},{\"Id\":12311301508090226,\"Name\":\"Dwight Mcneil\",\"Status\":2,\"StartPrice\":\"10.0\",\"Price\":\"10.0\",\"LastUpdate\":\"2022-02-10T00:03:23.0961628Z\"}]}]}]}]}}"
        val message = Json.decodeFromString(MessageSerializer, incomeData)

        Assertions.assertTrue(message is MarketUpdateMessage)
        val marketUpdateMessage = message as MarketUpdateMessage
        assertEquals(Message.Type.MARKET_UPDATE, marketUpdateMessage.header.type)
        assertEquals(UUID.fromString("99498c28-c20b-4fb3-968c-ad614613e9a0"), marketUpdateMessage.header.msgGuid)
        assertEquals(8090226, marketUpdateMessage.body.events.first().fixtureId)
        assertEquals(
            13.0,
            marketUpdateMessage.body.events.first().markets.first().providers.first().bets.first().startPrice
        )
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
        assertEquals(
            Bet.Settlement.LOSER,
            settlementMessage.body.events.first().markets.first().providers.first().bets.first().settlement
        )
    }

    @Test
    fun `outright leagues message deserialization`() {
        val incomeData =
            "{\"Header\":{\"Type\":38,\"MsgId\":10,\"MsgGuid\":\"c64c8f2b-de0b-412b-a927-423f1fc3384b\",\"ServerTimestamp\":1644313411},\"Body\":{\"Competition\":{\"Id\":40567,\"Name\":\"ATP Rome\",\"Type\":3,\"Competitions\":[{\"Id\":2021,\"Name\":\"2021\",\"Type\":4,\"Events\":[{\"FixtureId\":6936388,\"Livescore\":null,\"Markets\":null,\"OutrightLeague\":{\"Sport\":{\"Id\":54094,\"Name\":\"Tennis\"},\"Location\":{\"Id\":215,\"Name\":\"Italy\"},\"LastUpdate\":\"2021-09-13T17:22:30.489242\",\"Status\":3}}]}]}}}"
        val message = Json.decodeFromString(MessageSerializer, incomeData)

        Assertions.assertTrue(message is OutrightLeaguesMessage)
        val outrightLeaguesMessage = message as OutrightLeaguesMessage
        assertEquals(Message.Type.OUTRIGHT_LEAGUES, outrightLeaguesMessage.header.type)
        assertEquals(UUID.fromString("c64c8f2b-de0b-412b-a927-423f1fc3384b"), outrightLeaguesMessage.header.msgGuid)
        assertEquals(6936388, outrightLeaguesMessage.body.competition.competitions!!.first().events!!.first().fixtureId)
    }

    @Test
    fun `outright leagues bad message deserialization`() {
        val incomeData = "{\"Header\":{\"Type\":38,\"MsgId\":28,\"MsgGuid\":\"74ce0625-a3bc-41d4-9a6b-a4159eb36200\",\"ServerTimestamp\":1644412183},\"Body\":{\"Competition\":{\"Id\":0,\"Name\":null,\"Type\":0}}}"
        val message = Json.decodeFromString(MessageSerializer, incomeData)

        Assertions.assertTrue(message is OutrightLeaguesMessage)
        val outrightLeaguesMessage = message as OutrightLeaguesMessage
        assertEquals(Message.Type.OUTRIGHT_LEAGUES, outrightLeaguesMessage.header.type)
        assertEquals(UUID.fromString("74ce0625-a3bc-41d4-9a6b-a4159eb36200"), outrightLeaguesMessage.header.msgGuid)
    }
}
