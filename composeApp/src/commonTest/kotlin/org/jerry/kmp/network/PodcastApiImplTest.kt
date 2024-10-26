package org.jerry.kmp.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.jerry.kmp.MockStubs
import org.jerry.kmp.MockStubs.Companion.mockSuccessPodcastListResponse
import org.jerry.kmp.network.impl.PodcastApiImpl
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class PodcastApiImplTest {
    private lateinit var mockEngine: MockEngine
    private lateinit var mockClient: HttpClient
    private lateinit var api: PodcastApiImpl

    @BeforeTest
    fun setup() {
        mockEngine = MockStubs.mockEngine
        mockClient = MockStubs.mockClient
        api = PodcastApiImpl(mockClient)
    }

    @Test
    fun `getTopList returns success`() = runTest {
        // When
        val result = api.getTopList()
        assertEquals(mockSuccessPodcastListResponse, result)
    }

    @Test
    fun `getTopList returns fail`() = runTest {
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
        api = PodcastApiImpl(mockClient)

        // When
        var isException = false
        try {
            api.getTopList()
        } catch (e: Exception){
            isException = true
        }
        assertTrue { isException }
    }
}