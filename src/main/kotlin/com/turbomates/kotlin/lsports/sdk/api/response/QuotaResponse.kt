@file:UseSerializers(OffsetDateTimeSerializer::class)

package com.turbomates.kotlin.lsports.sdk.api.response

import com.turbomates.kotlin.lsports.sdk.serializer.OffsetDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.OffsetDateTime

@Serializable
data class QuotaResponse(
    @SerialName("Header")
    override val header: ApiResponse.Header,
    @SerialName("Body")
    override val body: Body
) : ApiResponse {
    @Serializable
    data class Body(
        @SerialName("CreditRemaining")
        val creditRemaining: Int,
        @SerialName("CreditLimit")
        val creditLimit: Int,
        @SerialName("UsedCredit")
        val usedCredit: Int,
        @SerialName("CurrentPeriodStartDate")
        val currentPeriodStartDate: OffsetDateTime,
        @SerialName("CurrentPeriodEndDate")
        val currentPeriodEndDate: OffsetDateTime
    ) : ApiResponse.Body
}
