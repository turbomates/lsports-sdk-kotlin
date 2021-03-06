package com.turbomates.kotlin.lsports.sdk.model

import java.time.LocalDate

data class OrderedFixtures(
    val fixtureOrders: List<Order>
) {
    data class Order(
        val fixtureId: Long,
        val providerId: Long,
        val orderDate: LocalDate
    )
}
