package com.turbomates.kotlin.lsports.sdk.api.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeaguesRequest(
    @SerialName("SportIds")
    var sportIds: List<Long>? = null,
    @SerialName("LocationIds")
    var locationIds: List<Long>? = null,
    @SerialName("SubscriptionStatus")
    var subscriptionStatus: SubscriptionStatus? = null
) : Request()
