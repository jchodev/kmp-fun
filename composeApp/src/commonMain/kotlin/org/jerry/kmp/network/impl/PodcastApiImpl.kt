package org.jerry.kmp.network.impl

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.jerry.kmp.data.remote.dto.PodcastDTO
import org.jerry.kmp.data.response.BasePaginationResponse
import org.jerry.kmp.network.PodcastApi

open class PodcastApiImpl (
    private val  client: HttpClient
) : PodcastApi {

    override suspend fun getTopList(): BasePaginationResponse<PodcastDTO> {
        return client.get("v1/toplist") {
            contentType(ContentType.Application.Json)
        }.body()
    }

}