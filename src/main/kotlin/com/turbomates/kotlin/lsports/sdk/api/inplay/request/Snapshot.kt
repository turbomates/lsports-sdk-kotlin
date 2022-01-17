package com.turbomates.kotlin.lsports.sdk.api.inplay.request

data class Snapshot(
    val packageId: String,
    val fixtureIds: List<Int>? = null,
)
