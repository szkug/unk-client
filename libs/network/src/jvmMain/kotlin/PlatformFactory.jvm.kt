package org.szkug.unk.network

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.logging.*


internal actual val PlatformFactory: IPlatformFactory = object : IPlatformFactory {

    override fun httpClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(Logging)
        }
    }
}