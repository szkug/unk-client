package org.szkug.keeting.network

import org.szkug.keeting.common.active.ActiveService
import org.szkug.keeting.common.active.KrpcActiveService
import org.szkug.keeting.domain.LocalProperties
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

    var debug by Delegates.notNull<Boolean>()

    private val mainCall by lazy { CallService.get(MAIN_HOST.get(debug)) }

    fun active(): ActiveService = KrpcActiveService(mainCall)

}