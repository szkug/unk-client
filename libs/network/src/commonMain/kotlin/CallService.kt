package org.szkug.keeting.network

import okio.ByteString.Companion.toByteString
import org.szkug.keeting.common.call.ProtocolServiceCall


interface CallService {

    suspend fun call(serviceName: String, funcName: String, request: ByteArray): ByteArray

    companion object Factory {
        fun get(host: String): CallService = KrpcService(host)
    }
}

private class KrpcService(val host: String) : CallService {
    companion object {
        private const val CALL_PATH = "/api/protocol/call"
    }

    private val handler = RequestHandler.get()

    override suspend fun call(serviceName: String, funcName: String, request: ByteArray): ByteArray {
        val call = ProtocolServiceCall(
            service_name = serviceName,
            call_function = funcName,
            placeholder = request.toByteString()
        )
        val url = host + CALL_PATH
        return handler.handle(url, call.encode())
    }
}