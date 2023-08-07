package com.turbomates.kotlin.lsports.sdk.listener.message

import com.turbomates.kotlin.lsports.sdk.model.Fixture
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
        val fixture: Fixture
    )
}
