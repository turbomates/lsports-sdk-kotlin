package com.turbomates.kotlin.lsports.sdk.listener

interface Handler<Message> {
    fun handle(message: Message)
}
