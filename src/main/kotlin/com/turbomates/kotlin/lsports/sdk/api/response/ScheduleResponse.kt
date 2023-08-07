package com.turbomates.kotlin.lsports.sdk.api.response

import com.turbomates.kotlin.lsports.sdk.model.Fixture
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleResponse(
    @SerialName("Header")
    override val header: ApiResponse.Header,
    @SerialName("Body")
    override val body: Body
) : ApiResponse {
    @Serializable
    data class Body(
        @SerialName("Fixtures")
        val fixtures: List<Fixture>
    ) : ApiResponse.Body
}
