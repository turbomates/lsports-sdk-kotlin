package com.turbomates.kotlin.lsports.sdk.api

import com.turbomates.kotlin.lsports.sdk.LSportConfig

class LSportAPI constructor(config: LSportConfig) {
    val live = LSportLiveAPI(config)
    val preLive = LSportPreLiveAPI(config)
}
