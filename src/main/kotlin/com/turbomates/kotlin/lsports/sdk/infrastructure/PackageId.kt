package com.turbomates.kotlin.lsports.sdk.infrastructure

typealias PackageId = String

internal fun PackageId.underscore() = "_${this}_"
