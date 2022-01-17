package com.turbomates.kotlin.lsports.sdk

import com.turbomates.kotlin.lsports.sdk.api.InPlayAPI
import com.turbomates.kotlin.lsports.sdk.api.PreMatchAPI
import com.turbomates.kotlin.lsports.sdk.api.inplay.request.Snapshot
import com.turbomates.kotlin.lsports.sdk.api.prematch.request.OutrightFixtures
import com.turbomates.kotlin.lsports.sdk.listener.Handler
import com.turbomates.kotlin.lsports.sdk.listener.Listener
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

class LSportsClient (
    configuration: LSportsConfig.() -> Unit
) {
    private val config = LSportsConfig().apply(configuration)
    private val hostConfig = LSportsConfig.HostConfig(config)

    val inPlay = InPlay(
        api = InPlayAPI(config),
        listener = Listener(hostConfig.inPlayHost)
    )
    val preMatch = PreMatch(
        api = PreMatchAPI(config),
        listener = Listener(hostConfig.preMatchHost)
    )

    class InPlay(
        val api: InPlayAPI,
        val listener: Listener
    )

    class PreMatch(
        val api: PreMatchAPI,
        val listener: Listener
    )
}
