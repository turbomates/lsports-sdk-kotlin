package com.turbomates.kotlin.lsports.sdk.api.request

import com.turbomates.kotlin.lsports.sdk.model.Language
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TranslationRequest(
    @SerialName("Languages")
    var languages: List<Language>,
    @SerialName("SportIds")
    var sportIds: List<Long>? = null,
    @SerialName("LocationIds")
    var locationIds: List<Long>? = null,
    @SerialName("LeagueIds")
    var leagueIds: List<Long>? = null,
    @SerialName("MarketIds")
    var marketIds: List<Long>? = null,
    @SerialName("ParticipantIds")
    var participantIs: List<Long>? = null
) : Request()
