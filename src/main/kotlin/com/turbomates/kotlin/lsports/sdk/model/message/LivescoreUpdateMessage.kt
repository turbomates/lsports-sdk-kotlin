package com.turbomates.kotlin.lsports.sdk.model.message

import com.turbomates.kotlin.lsports.sdk.model.body.LivescoreUpdateBody
import com.turbomates.kotlin.lsports.sdk.model.header.LivescoreUpdateHeader

data class LivescoreUpdateMessage(
    override val header: LivescoreUpdateHeader,
    val body: LivescoreUpdateBody
) : Message
