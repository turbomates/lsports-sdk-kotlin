package com.turbomates.kotlin.lsports.sdk.client.api

import com.turbomates.kotlin.lsports.sdk.client.LSportConfig

class LSportAPI constructor(config: LSportConfig) {
    val live = LSportLiveAPI(config)
    val preLive = LSportPreLiveAPI(config)
}
