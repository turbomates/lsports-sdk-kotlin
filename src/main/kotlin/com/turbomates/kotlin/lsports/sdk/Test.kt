package com.turbomates.kotlin.lsports.sdk

suspend fun main() {
    val client = LSportsClient {
        username = "bugrahan.cilsal@gmail.com"
        password = "Ha2325jfs!"
        inPlayPackageId = "1607"
        preMatchPackageId = "1608"
    }

    client.preMatch.api.
}
