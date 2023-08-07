package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.Serializable

@Serializable
enum class Subscription(val value: String) {
    SUBSCRIBED("1"),
    NOT_SUBSCRIBED("2")
}
