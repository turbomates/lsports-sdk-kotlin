package com.turbomates.kotlin.lsports.sdk

class LSportsConfig {
    lateinit var username: String
    lateinit var password: String

    val port: Int = 5672
    val requestHeartbeat: Int = 580
    val networkRecoveryInterval: Long = 1160
    val isAutomaticRecoveryEnabled: Boolean = true
    val virtualHost: String = "Customers"
    val liveHost: String = "inplay-rmq.lsports.eu"
    val preLiveHost: String = "prematch-rmq.lsports.eu"
    val preMatchUrl: String = "https://prematch.lsports.eu/OddService"
    val inPlayUrl: String = "https://inplay.lsports.eu/api"
}
