package com.turbomates.kotlin.lsports.sdk.client

interface Handler<Message> {
    fun handle(message: Message)
}
