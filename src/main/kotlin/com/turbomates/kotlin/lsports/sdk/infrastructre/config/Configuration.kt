package com.turbomates.kotlin.lsports.sdk.infrastructre.config

import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.EnvironmentVariablesPropertySource
import com.sksamuel.hoplite.PropertySource

class Configuration {
    companion object {
        fun load(): Config {
            return ConfigLoader.Builder()
                .addSource(PropertySource.resource("/local.properties", optional = true))
                .addSource(PropertySource.resource("/default.properties", optional = true))
                .addSource(
                    EnvironmentVariablesPropertySource(
                        useUnderscoresAsSeparator = true,
                        allowUppercaseNames = true
                    )
                )
                .build()
                .loadConfigOrThrow<LSport>().lsport.sdk
        }
    }
}

data class LSport(val lsport: SDK)
data class SDK(val sdk: Config)
data class Config(val rabbit: Rabbit)
data class Rabbit(
    val host: Host = Host(),
    val queue: Queue = Queue(),
    val username: String = "<username>",
    val password: String = "<password>",
    val virtualHost: String = "<virtual_host>",
    val autoRecovery: Boolean = false,
    val networkRecovery: Long = 5000,
    val heartbeat: Int = 60,
    val port: Int = 8080,
) {
    data class Host(
        val live: String = "<live_host>",
        val preLive: String = "<prelive_host>"
    )

    data class Queue(
        val live: String = "<queue>",
        val preLive: String = "<preLive_queue>"
    )
}
