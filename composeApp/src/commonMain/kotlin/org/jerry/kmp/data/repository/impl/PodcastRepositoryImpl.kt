package org.jerry.kmp.data.repository.impl

import org.jerry.kmp.data.Podcast
import org.jerry.kmp.data.repository.PodcastRepository
import org.jerry.kmp.data.toPodcast
import org.jerry.kmp.network.PodcastApi
import org.jerry.kmp.utilities.Resource

class PodcastRepositoryImpl(
    private val api: PodcastApi
): PodcastRepository {

    override suspend fun getTopList(): Resource<List<Podcast>> {
        return when (val response = api.getTopList()) {
            is Resource.Success -> {
                Resource.Success(
                    response.data!!.results.map { it.toPodcast() }
                )
            }
            is Resource.Error -> {
                Resource.Error(response.throwableWithMessage!!)
            }
        }
    }
}