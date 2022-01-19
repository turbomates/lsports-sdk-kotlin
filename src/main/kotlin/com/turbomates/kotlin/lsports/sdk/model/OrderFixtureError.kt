package com.turbomates.kotlin.lsports.sdk.model

data class OrderFixtureError(
    val fixtureId: Long,
    val sportId: Long? = null,
    val providerId: Long? = null,
    val errorCode: Int,
    val error: String
)
