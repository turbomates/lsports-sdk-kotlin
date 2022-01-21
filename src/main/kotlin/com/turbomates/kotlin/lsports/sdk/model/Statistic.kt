package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Statistic(
    @SerialName("Type")
    val type: Type,
    @SerialName("Results")
    val results: List<Result>,
    @SerialName("Incidents")
    val incidents: List<Incident>? = null
) {
    @Serializable
    enum class Type(val value: Int) {
        @SerialName("1")
        CORNER(1),
        @SerialName("2")
        UNKNOWN_2(2),
        @SerialName("3")
        UNKNOWN_3(3),
        @SerialName("4")
        UNKNOWN_4(4),
        @SerialName("5")
        UNKNOWN_5(5),
        @SerialName("6")
        YELLOW_CARD(6),
        @SerialName("7")
        RED_CARD(7),
        @SerialName("8")
        PENALTY(8),
        @SerialName("9")
        GOAL(9),
        @SerialName("10")
        SUBSTITUTION(10),
        @SerialName("11")
        UNKNOWN_11(11),
        @SerialName("12")
        FOULS(12),
        @SerialName("13")
        UNKNOWN_13(13),
        @SerialName("14")
        UNKNOWN_14(14),
        @SerialName("15")
        UNKNOWN_15(15),
        @SerialName("16")
        UNKNOWN_16(16),
        @SerialName("17")
        UNKNOWN_17(17),
        @SerialName("20")
        ACE(20),
        @SerialName("21")
        DOUBLE_FAULT(21),
        @SerialName("23")
        UNKNOWN_23(23),
        @SerialName("24")
        OWN_GOAL(24),
        @SerialName("25")
        PENALTY_GOAL(25),
        @SerialName("27")
        SCORE(27),
        @SerialName("28")
        TWO_POINT(28),
        @SerialName("30")
        THREE_POINT(30),
        @SerialName("31")
        TIME_OUT(31),
        @SerialName("32")
        FREE_THROW(32),
        @SerialName("33")
        HIT(33),
        @SerialName("34")
        FIRST_SERVE_WIN(34),
        @SerialName("40")
        MISSED_PENALTY(40)
    }
}
