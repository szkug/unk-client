package org.szkug.unk.repository

import org.szkug.unk.common.active.ActiveReq
import org.szkug.unk.network.NetServices

interface CommonRepository {


    suspend fun reportActive()
}

private class CommonRepositoryImpl: CommonRepository {

    private val activeService = NetServices.active()

    override suspend fun reportActive() {
        activeService.report(ActiveReq())
    }

}