package org.jerry.kmp.network

import org.jerry.kmp.data.remote.dto.PodcastDTO
import org.jerry.kmp.data.response.BasePaginationResponse

interface PodcastApi {
    suspend fun getTopList(): BasePaginationResponse<PodcastDTO>
}