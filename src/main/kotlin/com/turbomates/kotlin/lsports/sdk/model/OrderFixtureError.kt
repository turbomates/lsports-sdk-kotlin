package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderFixtureError(
    @SerialName("FixtureId")
    val fixtureId: Long,
    @SerialName("SportId")
    val sportId: Long? = null,
    @SerialName("ProviderId")
    val providerId: Long? = null,
    @SerialName("ErrorCode")
    val errorCode: Int,
    @SerialName("Error")
    val error: String
)
