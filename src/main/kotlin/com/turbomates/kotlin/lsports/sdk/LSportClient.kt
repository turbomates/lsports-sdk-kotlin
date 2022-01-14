package com.turbomates.kotlin.lsports.sdk

import com.turbomates.kotlin.lsports.sdk.api.LSportAPI
import com.turbomates.kotlin.lsports.sdk.listener.LSportListener

class LSportClient (
    configuration: LSportConfig.() -> Unit
){
    private val config = LSportConfig().apply(configuration)

    val api = LSportAPI(config)
    val listener = LSportListener(config)
}
