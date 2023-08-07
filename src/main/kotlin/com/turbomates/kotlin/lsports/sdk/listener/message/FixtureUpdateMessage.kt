package com.turbomates.kotlin.lsports.sdk.listener.message

import com.turbomates.kotlin.lsports.sdk.model.Fixture
import com.turbomates.kotlin.lsports.sdk.model.Livescore
import com.turbomates.kotlin.lsports.sdk.model.Market
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class FixtureUpdateMessage(
    @SerialName("Header")
    override val header: Message.Header,
    @SerialName("Body")
    val body: Body
) : Message {
    @Serializable
    data class Body(
        @SerialName("Events")
        val events: List<Event>
    )

    @Serializable
    data class Event(
        @SerialName("FixtureId")
        val fixtureId: Long,
        @SerialName("Fixture")
        val fixture: Fixture,
        @SerialName("Livescore")
        val livescore: Livescore? = null,
        @SerialName("Markets")
        val markets: List<JsonElement>? = null,
    )
}
