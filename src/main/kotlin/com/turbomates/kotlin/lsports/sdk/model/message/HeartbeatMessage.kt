package com.turbomates.kotlin.lsports.sdk.model.message

import com.turbomates.kotlin.lsports.sdk.model.header.HeartbeatHeader

data class HeartbeatMessage(
    override val header: HeartbeatHeader
) : Message
