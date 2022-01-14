package com.turbomates.kotlin.lsports.sdk.model

class Participant(
    val id: Int,
    val name: String,
    val rotationId: Int?,
    val position: String?,
    val isActive: Boolean?,
    val extraData: ExtraData?
)
