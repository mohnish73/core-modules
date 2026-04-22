package com.binary.mobile.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

internal actual fun provideEngine(): HttpClientEngine = OkHttp.create()
