package com.turbomates.kotlin.lsports.sdk.listener.message

import com.turbomates.kotlin.lsports.sdk.model.Event

interface EventsMessage : Message {
    val body: Body

    interface Body {
        val events: List<Event>
    }
}
