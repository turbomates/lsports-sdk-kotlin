package com.turbomates.kotlin.lsports.sdk

class LSportsConfig {
    lateinit var username: String
    lateinit var password: String
    lateinit var guid: String
    lateinit var inPlayPackageId: String
    lateinit var preMatchPackageId: String

    val port: Int = 5672
    val requestHeartbeat: Int = 580
    val networkRecoveryInterval: Long = 1160
    val isAutomaticRecoveryEnabled: Boolean = true
    val virtualHost: String = "Customers"
    val inPlayHost: String = "inplay-rmq.lsports.eu"
    val preMatchHost: String = "prematch-rmq.lsports.eu"
    val preMatchUrl: String = "https://prematch.lsports.eu/OddService"
    val inPlayUrl: String = "https://inplay.lsports.eu/api"

    fun inPlayQueueName() = "_${inPlayPackageId}_"
    fun preMatchQueueName() = "_${preMatchPackageId}_"
}
