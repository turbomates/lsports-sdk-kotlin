package com.turbomates.kotlin.lsports.sdk.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface ApiResponse : Response {
    override val header: Header
    override val body: Body

    @Serializable
    data class Header(
        @SerialName("HttpStatusCode")
        val httpStatusCode: Int,
        @SerialName("Errors")
        val errors: List<Error>? = null
    ) : Response.Header

    @Serializable
    data class Error(
        @SerialName("Message")
        val message: String
    )

    interface Body : Response.Body
}
