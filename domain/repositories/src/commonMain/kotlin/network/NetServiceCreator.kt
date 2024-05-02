package org.szkug.keeting.network

import org.szkug.keeting.common.active.ActiveService
import org.szkug.keeting.common.active.KrpcActiveService
import org.szkug.keeting.network.CallService

private data class NetHost(
    val debug: String,
    val release: String
)


class NetServices(
    val debug: Boolean
) {

}