package com.turbomates.kotlin.lsports.sdk.model

data class Incident(
    val period: Period.Type,
    val incidentType: Type,
    val seconds: Long,
    val participantPosition: Int,
    val results: List<Result>,
    val playerName: String? = null
) {
    enum class Type(val value: Int) {
        CORNER(1),
        YELLOW_CARD(6),
        RED_CARD(7),
        PENALTY(8),
        GOAL(9),
        SUBSTITUTION(10),
        FOULS(12),
        ACE(20),
        DOUBLE_FAULT(21),
        OWN_GOAL(24),
        PENALTY_GOAL(25),
        SCORE(27),
        TWO_POINT(28),
        THREE_POINT(30),
        TIME_OUT(31),
        FREE_THROW(32),
        HIT(33),
        FIRST_SERVE_WIN(34),
        MISSED_PENALTY(40)
    }
}
