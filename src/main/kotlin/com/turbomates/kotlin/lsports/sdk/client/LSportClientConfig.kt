package com.turbomates.kotlin.lsports.sdk.client

class LSportClientConfig {
    lateinit var username: String
    lateinit var password: String

    val port: Int = 5672
    val requestHeartbeat: Int = 580
    val networkRecoveryInterval: Long = 1160
    val isAutomaticRecoveryEnabled: Boolean = true
    val virtualHost: String = "Customers"
    val liveHost: String = "inplay-rmq.lsports.eu"
    val preLiveHost: String = "prematch-rmq.lsports.eu"
}
