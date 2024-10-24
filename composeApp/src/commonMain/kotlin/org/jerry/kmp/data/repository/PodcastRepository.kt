package org.jerry.kmp.data.repository

import org.jerry.kmp.data.Podcast
import org.jerry.kmp.data.remote.dto.PodcastDTO
import org.jerry.kmp.data.response.BasePaginationResponse
import org.jerry.kmp.utilities.Resource

interface PodcastRepository {
    suspend fun getTopList(): Resource<List<Podcast>>
}