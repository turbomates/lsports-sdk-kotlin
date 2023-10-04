@file:UseSerializers(OffsetDateTimeSerializer::class)

package com.turbomates.kotlin.lsports.sdk.listener.message

import com.turbomates.kotlin.lsports.sdk.model.League
import com.turbomates.kotlin.lsports.sdk.model.Livescore
import com.turbomates.kotlin.lsports.sdk.model.Location
import com.turbomates.kotlin.lsports.sdk.model.Participant
import com.turbomates.kotlin.lsports.sdk.model.Sport
import com.turbomates.kotlin.lsports.sdk.model.Subscription
import com.turbomates.kotlin.lsports.sdk.serializer.OffsetDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonElement
import java.time.OffsetDateTime

@Serializable
data class LivescoreUpdateMessage(
    @SerialName("Header")
    override val header: Message.Header,
    @SerialName("Body")
    override val body: Body
) : EventsMessage {
    @Serializable
    data class Body(
        @SerialName("Events")
        override val events: List<Event>
    ) : EventsMessage.Body

    @Serializable
    data class Event(
        @SerialName("FixtureId")
        override val fixtureId: Long,
        @SerialName("Fixture")
        val fixture: Fixture,
        @SerialName("Livescore")
        val livescore: Livescore,
        @SerialName("Markets")
        val markets: List<JsonElement>? = null
    ) : com.turbomates.kotlin.lsports.sdk.model.Event

    @Serializable
    data class Fixture(
        @SerialName("Subscription")
        val subscription: Subscription? = null,
        @SerialName("Sport")
        val sport: Sport,
        @SerialName("Location")
        val location: Location? = null,
        @SerialName("League")
        val league: League? = null,
        @SerialName("LastUpdate")
        val lastUpdate: OffsetDateTime,
        @SerialName("Participants")
        val participants: List<Participant>
    )
}
