package org.szkug.unk.network

import okio.ByteString.Companion.toByteString
import org.szkug.unk.common.call.ProtocolServiceCall
import org.szkug.krpc.service.Call


interface CallService : Call {

    companion object Factory {
        fun get(host: String): CallService = KrpcService(host)
    }
}

private class KrpcService(val host: String) : CallService {
    companion object {
        private const val CALL_PATH = "/api/protocol/call"
    }

    private val handler = RequestHandler.get()

    override suspend operator fun invoke(serviceName: String, functionName: String, requestData: ByteArray): ByteArray {
        val call = ProtocolServiceCall(
            service_name = serviceName,
            call_function = functionName,
            placeholder = requestData.toByteString()
        )
        val url = host + CALL_PATH
        return handler.handle(url, call.encode())
    }
}