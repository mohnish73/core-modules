package com.binary.mobile.serialization

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val AppJson: Json = Json {
    ignoreUnknownKeys = true
    isLenient         = true
    encodeDefaults    = true
    prettyPrint       = false
}

inline fun <reified T> T.toJson(): String = AppJson.encodeToString(this)

inline fun <reified T> String.fromJson(): T = AppJson.decodeFromString(this)

inline fun <reified T> String.fromJsonOrNull(): T? = runCatching { fromJson<T>() }.getOrNull()
