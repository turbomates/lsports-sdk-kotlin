package com.turbomates.kotlin.lsports.sdk.model.response

import com.turbomates.kotlin.lsports.sdk.model.League
import com.turbomates.kotlin.lsports.sdk.model.Message
import com.turbomates.kotlin.lsports.sdk.model.Header
import com.turbomates.kotlin.lsports.sdk.model.Response
import kotlinx.datetime.LocalDateTime

data class LeaguesResponse(
    override val header: HeaderImpl,
    val body: List<LeagueImpl>
) : Response {
    data class HeaderImpl(
        override val type: Message.Type,
        override val serverTimestamp: LocalDateTime
    ) : Header

    data class LeagueImpl(
        override val id: Long,
        override val name: String,
        val locationId: Long,
        val sportId: Long,
        val season: String
    ) : League
}
