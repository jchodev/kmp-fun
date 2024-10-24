package org.jerry.kmp.network

import assertk.all
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.BeforeTest
import kotlin.test.Test
import assertk.assertThat
import assertk.assertions.*
import org.jerry.kmp.utilities.Resource
import kotlin.test.assertEquals

class PodcastApiTest {
    private lateinit var mockEngine: MockEngine
    private lateinit var mockClient: HttpClient
    private lateinit var api: PodcastApi

    @BeforeTest
    fun setup() {
        mockEngine = MockEngine { request ->
            when (request.url.fullPath) {
                "/v1/toplist" -> {
                    respond(
                        content = """
                        {
                            "results": [{
                                "author": "Test Author",
                                "description": "Test Description",
                                "id": 1,
                                "image": "test.jpg",
                                "link": "https://test.com",
                                "title":"title"
                            }],
                            "limit": 10,
                            "offset": 10,
                            "total": 1
                        }
                        """.trimIndent(),
                        status = HttpStatusCode.OK,
                        headers = headersOf(HttpHeaders.ContentType, "application/json")
                    )
                }
                else -> respond(
                    content = "Not Found",
                    status = HttpStatusCode.NotFound
                )
            }
        }

        mockClient = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }
        }

        api = PodcastApi(mockClient)
    }

    @Test
    fun `getTopList returns success response when API call is successful`() = runTest {
        // When
        val result = api.getTopList()

        // Then
        assertThat(result).isInstanceOf(Resource.Success::class)

        val successResult = result as Resource.Success

        val firstItem = successResult.data!!.results[0]
        assertEquals(1, firstItem.id)
        assertEquals("Test Author", firstItem.author)
    }

    @Test
    fun `getTopList returns error response when API call fails`() = runTest {
        // Given
        mockEngine = MockEngine { request ->
            respond(
                content = "Error",
                status = HttpStatusCode.InternalServerError
            )
        }
        mockClient = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }
        }
        api = PodcastApi(mockClient)

        // When
        val result = api.getTopList()

        // Then
        assertThat(result).isInstanceOf(Resource.Error::class)
    }


}