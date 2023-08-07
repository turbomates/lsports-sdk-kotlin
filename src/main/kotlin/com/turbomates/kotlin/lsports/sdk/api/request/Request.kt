package com.turbomates.kotlin.lsports.sdk.api.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class Request {
    @SerialName("UserName")
    lateinit var username: String

    @SerialName("Password")
    lateinit var password: String

    @SerialName("PackageId")
    lateinit var packageId: String
}

