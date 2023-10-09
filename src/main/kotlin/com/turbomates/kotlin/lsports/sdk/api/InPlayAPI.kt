package com.turbomates.kotlin.lsports.sdk.api

import com.turbomates.kotlin.lsports.sdk.LSportsConfig
import com.turbomates.kotlin.lsports.sdk.api.request.ScheduleRequest
import com.turbomates.kotlin.lsports.sdk.api.request.SnapshotRequest
import com.turbomates.kotlin.lsports.sdk.api.response.QuotaResponse
import com.turbomates.kotlin.lsports.sdk.api.response.ScheduleResponse
import com.turbomates.kotlin.lsports.sdk.api.response.SnapshotResponse

class InPlayAPI(config: LSportsConfig) : API(config, Type.IN_PLAY) {

    suspend fun packageQuota(): QuotaResponse =
        request("${config.apiUrl}/Package/GetPackageQuota")

    suspend fun schedule(sportIds: List<Long>, requestBlock: ScheduleRequest.() -> Unit = {}): ScheduleResponse =
        request("${config.apiUrl}/Fixtures/InPlaySchedule", ScheduleRequest(sportIds).apply(requestBlock))

    enum class SnapshotAction(override val value: String) : API.SnapshotAction {
        GET_EVENTS("GetEvents"),
        GET_FIXTURE_MARKET("GetFixtureMarkets"),
        GET_FIXTURES("GetFixtures"),
        GET_SCORES("GetScores")
    }
}
