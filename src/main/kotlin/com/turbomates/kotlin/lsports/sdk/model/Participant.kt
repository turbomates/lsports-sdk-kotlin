package com.turbomates.kotlin.lsports.sdk.model

interface Participant {
    val id: Long
    val name: String
    val rotationId: Long?
    val position: Int?
    val isActive: Boolean?
    val participantExtraData: ExtraData?
}
