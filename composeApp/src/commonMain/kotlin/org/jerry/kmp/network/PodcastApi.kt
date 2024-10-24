package org.jerry.kmp.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.jerry.kmp.data.remote.dto.PodcastDTO
import org.jerry.kmp.data.response.BasePaginationResponse
import org.jerry.kmp.utilities.Resource
import org.jerry.kmp.utilities.ThrowableWithMessage

open class PodcastApi(
    private val client: HttpClient
) {
    open suspend fun getTopList(): Resource<BasePaginationResponse<PodcastDTO>> {
        return try {
            val response = client.get("v1/toplist") {
                contentType(ContentType.Application.Json)
            }
            Resource.Success(response.body<BasePaginationResponse<PodcastDTO>>())
        } catch (e: Exception) {
            Resource.Error(
                throwableWithMessage = ThrowableWithMessage(
                    throwable = e
                )
            )
        }
    }
}