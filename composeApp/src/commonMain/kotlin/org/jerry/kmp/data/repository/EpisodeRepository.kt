package org.jerry.kmp.data.repository

import kotlinx.coroutines.flow.Flow
import org.jerry.kmp.data.Episode
import org.jerry.kmp.utilities.Resource

interface EpisodeRepository {
    suspend fun getPodcastsByPodcastId(podcastId: Long): Flow<Resource<List<Episode>>>
}