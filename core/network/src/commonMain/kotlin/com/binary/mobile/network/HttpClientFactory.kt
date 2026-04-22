package com.binary.mobile.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(
    enableLogging: Boolean = true,
    json: Json = Json { ignoreUnknownKeys = true; isLenient = true }
): HttpClient = HttpClient(provideEngine()) {

    install(ContentNegotiation) {
        json(json)
    }

    if (enableLogging) {
        install(Logging) {
            level = LogLevel.HEADERS
        }
    }
}

internal expect fun provideEngine(): HttpClientEngine
