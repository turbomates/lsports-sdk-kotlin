@file:UseSerializers(HttpStatusCodeSerializer::class)

package com.turbomates.kotlin.lsports.sdk.api.response

import com.turbomates.kotlin.lsports.sdk.serializer.HttpStatusCodeSerializer
import kotlinx.serialization.UseSerializers

sealed interface Response {
    val header: Header
    val body: Body

    interface Header
    interface Body
}
