package com.turbomates.kotlin.lsports.sdk.listener.message

import com.turbomates.kotlin.lsports.sdk.model.Livescore
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LivescoreUpdateMessage(
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
        @SerialName("Livescore")
        val livescore: Livescore
    )
}
