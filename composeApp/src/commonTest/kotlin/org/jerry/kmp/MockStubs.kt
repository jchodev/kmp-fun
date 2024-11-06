package org.jerry.kmp

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jerry.kmp.data.Episode
import org.jerry.kmp.data.Podcast
import org.jerry.kmp.data.database.entity.Favourite
import org.jerry.kmp.data.remote.dto.EpisodeDTO
import org.jerry.kmp.data.remote.dto.PodcastDTO
import org.jerry.kmp.data.response.BasePaginationResponse
import org.jerry.kmp.data.toEpisode
import org.jerry.kmp.data.toPodcast
import org.jerry.kmp.utilities.Resource
import org.jerry.kmp.utilities.ThrowableWithMessage


class MockStubs {

    companion object {
        val mockPodcastDTOS = (1..40).map { i ->
            PodcastDTO(
                id = i.toLong(),
                author = "author$i",
                image = "image$i",
                description = "description$i",
                link = "link$i",
                title = "title$i"
            )
        }

        val mockEpisodeDTOS = (1..20).map { i ->
            EpisodeDTO(
                description = "description$i",
                duration = i.toLong(),
                id = i.toLong(),
                mimeType = if (i % 2 == 0) "video/mp4" else "audio/mpeg",
                published = Long.MAX_VALUE,
                type = "type$i",
                title = "title$i",
                url = "url$i"
            )
        }

        val mockServerErrorCode = 400
        val mockExceptionStr = "Some Error"
        val mockNormalException = Exception(mockExceptionStr)

        val mockSuccessPodcastListResourceDTO : Resource<List<Podcast>> = Resource.Success(data = mockPodcastDTOS.map { it.toPodcast() })
        val mockErrorPodcastListResourceDTO : Resource<List<Podcast>> = Resource.Error(throwableWithMessage = ThrowableWithMessage(message = mockExceptionStr))

        val mockSuccessEpisodeListResourceDTO : Resource<List<Episode>> = Resource.Success(data = mockEpisodeDTOS.map { it.toEpisode() })
        val mockErrorEpisodeListResourceDTO : Resource<List<Episode>> = Resource.Error(throwableWithMessage = ThrowableWithMessage(message = mockExceptionStr))

        val mockSuccessPodcastListResponse = BasePaginationResponse(
            results = mockPodcastDTOS
        )
        val responseHeaders = headersOf(HttpHeaders.ContentType, "application/json")
        val mockEngine = MockEngine { request ->
            when (request.url.fullPath) {
                "/v1/toplist" -> {
                    respond(
                        content = Json.encodeToString(
                            mockSuccessPodcastListResponse
                        ),
                        status = HttpStatusCode.OK,
                        headers = responseHeaders
                    )
                }
                else ->{
                    respond(
                        content = "Not Found",
                        status = HttpStatusCode.NotFound
                    )
                }
            }
        }
        val mockClient = HttpClient(mockEngine) {
            install(plugin = ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }
        }

        val mockFavourite = Favourite(
            podcastId = 1L,
            createdAt = 1L
        )

        val mockFavourites = listOf(
            mockFavourite
        )
    }
}

