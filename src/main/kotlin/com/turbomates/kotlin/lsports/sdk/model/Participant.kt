package com.turbomates.kotlin.lsports.sdk.model

data class Participant(
    val id: Long,
    val name: String,
    val rotationId: Long? = null,
    val position: Int? = null,
    val isActive: Boolean? = null,
    val participantExtraData: ExtraData? = null
)
