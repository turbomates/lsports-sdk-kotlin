package com.turbomates.kotlin.lsports.sdk.api

import com.turbomates.kotlin.lsports.sdk.LSportConfig

class LSportLiveAPI(private val config: LSportConfig) {

    fun schedule() {

    }

    companion object {
        private const val SCHEDULE = "https://inplay.lsports.eu/api/schedule/GetInPlaySchedule"
        private const val ORDER_FIXTURE = "https://inplay.lsports.eu/api/schedule/OrderFixtures"
        private const val CANCEL_ORDER_FIXTURE = "https://inplay.lsports.eu/api/schedule/CancelFixtureOrders"
        private const val ORDERED_FIXTURE = "https://inplay.lsports.eu/api/schedule/GetOrderedFixtures"
        private const val SNAPSHOT = "https://inplay.lsports.eu/api/Snapshot/"
        private const val ENABLE_PACKAGE = "https://lsports.api-docs.io/inplay.lsports.eu/api/Package/EnablePackage"
        private const val DISABLE_PACKAGE = "https://lsports.api-docs.io/inplay.lsports.eu/api/Package/DisablePackage"
    }
}
