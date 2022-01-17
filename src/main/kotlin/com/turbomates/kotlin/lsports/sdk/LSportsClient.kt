package com.turbomates.kotlin.lsports.sdk

import com.turbomates.kotlin.lsports.sdk.api.InPlayAPI
import com.turbomates.kotlin.lsports.sdk.api.PreMatchAPI
import com.turbomates.kotlin.lsports.sdk.listener.InPlayListener
import com.turbomates.kotlin.lsports.sdk.listener.PreMatchListener

class LSportsClient (
    configuration: LSportsConfig.() -> Unit
) {
    private val config = LSportsConfig().apply(configuration)

    val inPlay = InPlay(
        api = InPlayAPI(config),
        listener = InPlayListener(config)
    )
    val preMatch = PreMatch(
        api = PreMatchAPI(config),
        listener = PreMatchListener(config))

    class InPlay(
        val api: InPlayAPI,
        val listener: InPlayListener
    )

    class PreMatch(
        val api: PreMatchAPI,
        val listener: PreMatchListener
    )
}
