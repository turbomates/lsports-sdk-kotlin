package com.turbomates.kotlin.lsports.sdk.model

data class Period(
    val type: Type,
    val isFinished: Boolean,
    val isConfirmed: Boolean,
    val results: List<Result>,
    val incidents: List<Incident>,
    val subPeriods: List<Period>? = null,
    val sequenceNumber: Int? = null
) {
    enum class Type(value: Int) {
        NSY(-1),
        FIRST_PART(1),
        SECOND_PART(2),
        THIRD_PART(3),
        FOURTH_PART(4),
        FIFTH_PART(5),
        SIXTH_PART(6),
        SEVENTH_PART(7),
        EIGHT_PART(8),
        EIGHTH_PART(9),
        FIRST_HALF(10),
        SECOND_HALF(20),
        OVERTIME_FIRST_HALF(30),
        OVERTIME_SECOND_HALF(35),
        OVERTIME(40),
        PENALTIES(50),
        GAME(60),
        ERROR(62),
        ROUND(65),
        FRAME(66),
        LEG(67),
        OVER(70),
        DELIVERY(71),
        BREAK_TIME(80),
        NONE(99),
        FULL_TIME(100),
        FULL_TIME_AFTER_OVERTIME(101),
        FULL_TIME_AFTER_PENALTIES(102)
    }
}
