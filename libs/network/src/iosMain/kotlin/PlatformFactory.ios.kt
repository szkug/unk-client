package org.szkug.keeting.network

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.logging.*

internal actual val PlatformFactory: IPlatformFactory = object : IPlatformFactory {

    override fun httpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging)
        }
    }
}