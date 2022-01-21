package com.turbomates.kotlin.lsports.sdk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Incident(
    @SerialName("Period")
    val period: Period.Type,
    @SerialName("IncidentType")
    val incidentType: Type,
    @SerialName("Seconds")
    val seconds: Long,
    @SerialName("ParticipantPosition")
    val participantPosition: Int,
    @SerialName("PlayerId")
    val playerId: Int? = null,
    @SerialName("Results")
    val results: List<Result>,
    @SerialName("PlayerName")
    val playerName: String? = null
) {
    @Serializable
    enum class Type(val value: Int) {
        @SerialName("0")
        UNKNOWN_0(0),
        @SerialName("1")
        CORNER(1),
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
        @SerialName("12")
        FOULS(12),
        @SerialName("20")
        ACE(20),
        @SerialName("21")
        DOUBLE_FAULT(21),
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
