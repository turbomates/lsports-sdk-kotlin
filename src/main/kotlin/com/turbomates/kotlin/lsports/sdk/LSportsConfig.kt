package com.turbomates.kotlin.lsports.sdk

import com.rabbitmq.client.ConnectionFactory

data class LSportsConfig(
    val username: String,
    val password: String,
    val inPlayPackageId: String,
    val preMatchPackageId: String
) {
    var apiRequestTimeoutMillis = 60L * 1000L
    var requestHeartbeat: Int = 580
    var isAutomaticRecoveryEnabled: Boolean = true
    var networkRecoveryInterval: Long = 1160
    val port: Int = 5672
    val apiUrl: String = "https://stm-api.lsports.eu"
    val snapshotApiUrl: String = " https://stm-snapshot.lsports.eu"
    val inPlayVirtualHost: String = "StmInPlay"
    val preMatchVirtualHost: String = "StmPreMatch"
    val inPlayHost: String = "stm-inplay.lsports.eu"
    val preMatchHost: String = "stm-prematch.lsports.eu"
}
