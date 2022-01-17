package com.turbomates.kotlin.lsports.sdk

import com.rabbitmq.client.ConnectionFactory

class LSportsConfig {
    lateinit var username: String
    lateinit var password: String

    private val port: Int = 5672
    private val requestHeartbeat: Int = 580
    private val networkRecoveryInterval: Long = 1160
    private val isAutomaticRecoveryEnabled: Boolean = true
    private val virtualHost: String = "Customers"
    val preMatchUrl: String = "https://prematch.lsports.eu/OddService"
    val inPlayUrl: String = "https://inplay.lsports.eu/api"
    val inPlayHost: String = "inplay-rmq.lsports.eu"
    val preMatchHost: String = "prematch-rmq.lsports.eu"

    data class HostConfig(private val config: LSportsConfig) {
        private val connectionFactory = ConnectionFactory().apply {
            port = config.port
            username = config.username
            password = config.password
            virtualHost = config.virtualHost
            requestedHeartbeat = config.requestHeartbeat
            networkRecoveryInterval = config.networkRecoveryInterval
            isAutomaticRecoveryEnabled = config.isAutomaticRecoveryEnabled
        }
        val inPlayHost = connectionFactory.apply { connectionFactory.host = config.inPlayHost }
        val preMatchHost = connectionFactory.apply { connectionFactory.host = config.preMatchHost }
    }
}
