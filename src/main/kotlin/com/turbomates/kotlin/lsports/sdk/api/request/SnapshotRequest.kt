@file:UseSerializers(OffsetDateTimeSerializer::class)

package com.turbomates.kotlin.lsports.sdk.api.request

import com.turbomates.kotlin.lsports.sdk.serializer.OffsetDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.OffsetDateTime

@Serializable
data class SnapshotRequest(
    @SerialName("Timestamp")
    var timestmap: OffsetDateTime? = null,
    @SerialName("FromDate")
    var fromDate: OffsetDateTime? = null,
    @SerialName("ToDate")
    var toDate: OffsetDateTime? = null,
    @SerialName("Sports")
    var sportIds: List<Long>? = null,
    @SerialName("Locations")
    var locationIds: List<Long>? = null,
    @SerialName("Leagues")
    var leagueIds: List<Long>? = null,
    @SerialName("Tournaments")
    var tournamentIds: List<Long>? = null,
    @SerialName("Fixtures")
    var fixtureIds: List<Long>? = null,
    @SerialName("Markets")
    var marketIds: List<Long>? = null
) : Request()
