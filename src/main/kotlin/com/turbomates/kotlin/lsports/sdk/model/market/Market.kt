package com.turbomates.kotlin.lsports.sdk.model.market

import com.turbomates.kotlin.lsports.sdk.model.provider.Provider

interface Market {
    val id: Long
    val name: String
    val providers: List<Provider>
}
