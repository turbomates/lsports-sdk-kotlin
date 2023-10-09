package com.turbomates.kotlin.lsports.sdk.api

import com.turbomates.kotlin.lsports.sdk.LSportsConfig
import com.turbomates.kotlin.lsports.sdk.api.request.SnapshotRequest
import com.turbomates.kotlin.lsports.sdk.api.response.SnapshotResponse

class PreMatchAPI(config: LSportsConfig) : API(config, Type.PRE_MATCH) {

    enum class SnapshotAction(override val value: String) : API.SnapshotAction {
        GET_EVENTS("GetEvents"),
        GET_FIXTURE_MARKET("GetFixtureMarkets"),
        GET_FIXTURES("GetFixtures"),
        GET_SCORES("GetScores"),
        GET_OUTRIGHT_EVENTS("GetOutrightEvents"),
        GET_OUTRIGHT_FIXTURE("GetOutrightFixture"),
        GET_OUTRIGHT_LEAGUES("GetOutrightLeagues"),
        GET_OUTRIGHT_LEAGUE_MARKETS("GetOutrightLeagueMarkets"),
        GET_OUTRIGHT_FIXTURE_MARKETS("GetOutrightFixtureMarkets"),
        GET_OUTRIGHT_SCORES("GetOutrightScores")
    }
}
