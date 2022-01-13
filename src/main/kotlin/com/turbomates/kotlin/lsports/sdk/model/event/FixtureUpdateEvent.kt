package com.turbomates.kotlin.lsports.sdk.model.event

import com.turbomates.kotlin.lsports.sdk.model.Fixture

data class FixtureUpdateEvent(
    override val fixtureId: Long,
    val fixture: Fixture
) : Event
