package org.jerry.kmp.network.impl

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.jerry.kmp.data.remote.dto.EpisodeDTO
import org.jerry.kmp.data.remote.dto.PodcastDTO
import org.jerry.kmp.data.response.BasePaginationResponse
import org.jerry.kmp.network.EpisodeApi

class EpisodeApiImpl(
    private val  client: HttpClient
) : EpisodeApi {

    override suspend fun getPodcastsByPodcastId(podcastId: Long): BasePaginationResponse<EpisodeDTO> {
        return client.get("/v1/podcasts/${podcastId}/episodes") {
            contentType(ContentType.Application.Json)
        }.body()
    }

}