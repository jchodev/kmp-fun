package org.jerry.kmp.data.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jerry.kmp.data.Episode
import org.jerry.kmp.data.repository.EpisodeRepository
import org.jerry.kmp.data.toEpisode
import org.jerry.kmp.data.toPodcast
import org.jerry.kmp.network.EpisodeApi
import org.jerry.kmp.utilities.Resource
import org.jerry.kmp.utilities.ThrowableWithMessage

class EpisodeRepositoryImpl(
    private val api: EpisodeApi
): EpisodeRepository {
    override suspend fun getPodcastsByPodcastId(podcastId: Long): Flow<Resource<List<Episode>>> = flow{
        try {
            val response = api.getPodcastsByPodcastId(podcastId)
            emit(
                Resource.Success(
                    response.results.map {
                        it.toEpisode()
                    }
                )
            )
        } catch (e: Exception){
            emit(Resource.Error(
                throwableWithMessage = ThrowableWithMessage(
                    throwable = e
                )
            ))
        }
    }

}
