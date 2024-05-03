package org.szkug.keeting.repository

import org.szkug.keeting.common.active.ActiveReq
import org.szkug.keeting.network.NetServices

interface CommonRepository {


    suspend fun reportActive()
}

private class CommonRepositoryImpl: CommonRepository {

    private val activeService = NetServices.active()

    override suspend fun reportActive() {
        activeService.report(ActiveReq())
    }

}