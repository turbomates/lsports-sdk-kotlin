package com.turbomates.kotlin.lsports.sdk.client.infrastructure

typealias PackageId = String

internal fun PackageId.underscore() = "_${this}_"
