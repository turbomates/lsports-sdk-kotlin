package com.turbomates.kotlin.lsports.sdk.model.message

import com.turbomates.kotlin.lsports.sdk.model.body.FixtureUpdateBody
import com.turbomates.kotlin.lsports.sdk.model.header.FixtureUpdateHeader

data class FixtureUpdateMessage(
    override val header: FixtureUpdateHeader,
    val body: FixtureUpdateBody
) : Message
