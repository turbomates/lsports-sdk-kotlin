package com.turbomates.kotlin.lsports.sdk.client

import com.turbomates.kotlin.lsports.sdk.client.api.LSportAPI
import com.turbomates.kotlin.lsports.sdk.client.listener.LSportListener

class LSportClient (
    configuration: LSportConfig.() -> Unit
){
    private val config = LSportConfig().apply(configuration)

    val api = LSportAPI(config)
    val listener = LSportListener(config)
}
