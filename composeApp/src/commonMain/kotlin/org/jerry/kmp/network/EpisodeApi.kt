package org.jerry.kmp.network

import org.jerry.kmp.data.remote.dto.EpisodeDTO
import org.jerry.kmp.data.response.BasePaginationResponse

interface EpisodeApi {
    suspend fun getPodcastsByPodcastId(
        podcastId: Long
    ): BasePaginationResponse<EpisodeDTO>
}