package org.jerry.kmp.data.repository

import kotlinx.coroutines.flow.Flow
import org.jerry.kmp.data.Podcast
import org.jerry.kmp.data.remote.dto.PodcastDTO
import org.jerry.kmp.data.response.BasePaginationResponse
import org.jerry.kmp.utilities.Resource

interface PodcastRepository {
    suspend fun getTopList(): Flow<Resource<List<Podcast>>>
}