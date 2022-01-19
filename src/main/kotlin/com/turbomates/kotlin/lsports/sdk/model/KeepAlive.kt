package com.turbomates.kotlin.lsports.sdk.model

data class KeepAlive(
    val activeEvents: List<Long>,
    val extraData: ExtraData? = null,
    val providerId: Int? = null
)
