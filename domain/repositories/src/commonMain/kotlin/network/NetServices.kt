package org.szkug.keeting.network

import org.szkug.keeting.common.Env
import org.szkug.keeting.common.active.KrpcActiveService
import org.szkug.keeting.common.inject.InjectUtil
import org.szkug.keeting.login.KrpcLoginService
import org.szkug.krpc.service.Service
import org.szkug.krpc.service.ServiceFactory
import kotlin.properties.Delegates

private data class NetHost(
    private val debugHost: String,
    private val releaseHost: String
) {
    fun get(debug: Boolean) = if (debug) debugHost else releaseHost
}

private val MAIN_HOST = NetHost(
    debugHost = LocalProperties.DEV_HOST,
    releaseHost = LocalProperties.RELEASE_HOST
)

object NetServices {

    private val env by InjectUtil.lazy<Env>()

    private val MAIN_CALL by lazy { CallService.get(MAIN_HOST.get(env.debug)) }

    private infix fun <S: Service> ServiceFactory<S>.by(call: CallService) = create(call)

    fun active() = KrpcActiveService.Factory by MAIN_CALL

    fun login() = KrpcLoginService.Factory by MAIN_CALL
}