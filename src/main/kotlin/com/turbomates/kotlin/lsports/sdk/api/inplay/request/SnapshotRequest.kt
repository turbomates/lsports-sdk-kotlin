package com.turbomates.kotlin.lsports.sdk.api.inplay.request

data class SnapshotRequest(
    val packageId: String,
    val fixtureIds: List<Int>? = null,
)
