package org.szkug.unk.network

import io.ktor.client.*


internal expect val PlatformFactory: IPlatformFactory


internal interface IPlatformFactory {

    fun httpClient(): HttpClient
}