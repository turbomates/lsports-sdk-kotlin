package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Period(
    @SerialName("Type")
    val type: Type,
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
) {
    @Serializable
    enum class Type(val value: Int) {
        @SerialName("-1")
        NSY(-1),
        @SerialName("1")
        FIRST_PART(1),
        @SerialName("2")
        SECOND_PART(2),
        @SerialName("3")
        THIRD_PART(3),
        @SerialName("4")
        FOURTH_PART(4),
        @SerialName("5")
        FIFTH_PART(5),
        @SerialName("6")
        SIXTH_PART(6),
        @SerialName("7")
        SEVENTH_PART(7),
        @SerialName("8")
        EIGHT_PART(8),
        @SerialName("9")
        EIGHTH_PART(9),
        @SerialName("10")
        FIRST_HALF(10),
        @SerialName("20")
        SECOND_HALF(20),
        @SerialName("30")
        OVERTIME_FIRST_HALF(30),
        @SerialName("35")
        OVERTIME_SECOND_HALF(35),
        @SerialName("40")
        OVERTIME(40),
        @SerialName("50")
        PENALTIES(50),
        @SerialName("60")
        GAME(60),
        @SerialName("62")
        ERROR(62),
        @SerialName("65")
        ROUND(65),
        @SerialName("66")
        FRAME(66),
        @SerialName("67")
        LEG(67),
        @SerialName("70")
        OVER(70),
        @SerialName("71")
        DELIVERY(71),
        @SerialName("80")
        BREAK_TIME(80),
        @SerialName("99")
        NONE(99),
        @SerialName("100")
        FULL_TIME(100),
        @SerialName("101")
        FULL_TIME_AFTER_OVERTIME(101),
        @SerialName("102")
        FULL_TIME_AFTER_PENALTIES(102)
    }
}
