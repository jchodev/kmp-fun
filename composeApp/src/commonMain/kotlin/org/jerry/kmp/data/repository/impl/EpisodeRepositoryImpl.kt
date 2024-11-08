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

    //we can change one time only
//    suspend fun getPodcastsByPodcastId2(podcastId: Long): Resource<List<Episode>> {
//        return try {
//            val response = api.getPodcastsByPodcastId(podcastId)
//            Resource.Success(response.results.map { it.toEpisode() })
//        } catch (e: Exception) {
//            Resource.Error(ThrowableWithMessage(throwable = e))
//        }
//    }

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
