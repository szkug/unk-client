package org.szkug.keeting.network

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

internal interface RequestHandler {

    suspend fun handle(url: String, request: ByteArray): ByteArray

    companion object Factory {
        fun get(): RequestHandler = KtorRequestHandler
    }
}


private object KtorRequestHandler : RequestHandler {

    private val client = PlatformFactory.httpClient()

    override suspend fun handle(url: String, request: ByteArray): ByteArray {
        val response = client.post(url) {
            contentType(ContentType.Application.Any)
            setBody(request)
        }
        return response.body()
    }
}