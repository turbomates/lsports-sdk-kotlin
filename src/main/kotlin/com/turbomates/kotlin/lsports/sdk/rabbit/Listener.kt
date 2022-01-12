package com.turbomates.kotlin.lsports.sdk.rabbit

interface Listener {
    fun handle(message: String)
}
