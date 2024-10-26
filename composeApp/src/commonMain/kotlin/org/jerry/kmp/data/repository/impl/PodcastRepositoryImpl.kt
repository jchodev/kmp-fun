package org.jerry.kmp.data.repository.impl

import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jerry.kmp.data.Podcast
import org.jerry.kmp.data.repository.PodcastRepository
import org.jerry.kmp.data.response.ApiErrorResponse
import org.jerry.kmp.data.toPodcast
import org.jerry.kmp.network.PodcastApi
import org.jerry.kmp.utilities.Resource
import org.jerry.kmp.utilities.ThrowableWithMessage

class PodcastRepositoryImpl(
    private val api: PodcastApi
): PodcastRepository {

    override suspend fun getTopList(): Flow<Resource<List<Podcast>>> = flow {
        try {
            val response = api.getTopList()
            emit(
                Resource.Success(
                    response.results.map { it.toPodcast() }
                )
            )
        } catch (e: ResponseException) {
            val errorBody = try {
                e.response.body<ApiErrorResponse>()
            } catch (parseError: Exception) {
                null
            }

            emit(Resource.Error(
                throwableWithMessage = ThrowableWithMessage(
                    message = errorBody?.message ?: "Unknow Http Error",
                )
            ))
        } catch (e: Exception){
            emit(Resource.Error(
                throwableWithMessage = ThrowableWithMessage(
                    throwable = e
                )
            ))
        }
    }
}