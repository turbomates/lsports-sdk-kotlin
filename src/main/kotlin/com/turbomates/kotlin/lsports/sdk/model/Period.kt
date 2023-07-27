package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Period(
    @SerialName("Type")
    val type: Int,
    @SerialName("IsFinished")
    val isFinished: Boolean,
    @SerialName("IsConfirmed")
    val isConfirmed: Boolean,
    @SerialName("Results")
    val results: List<Result>,
    @SerialName("Incidents")
    val incidents: List<Incident>? = null,
    @SerialName("SubPeriods")
    val subPeriods: List<Period>? = null,
    @SerialName("SequenceNumber")
    val sequenceNumber: Int? = null
)
