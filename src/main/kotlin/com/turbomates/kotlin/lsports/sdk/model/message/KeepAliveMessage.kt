package com.turbomates.kotlin.lsports.sdk.model.message

import com.turbomates.kotlin.lsports.sdk.model.body.KeepAliveBody
import com.turbomates.kotlin.lsports.sdk.model.header.KeepAliveHeader

data class KeepAliveMessage(
    override val header: KeepAliveHeader,
    val body: KeepAliveBody
) : Message
