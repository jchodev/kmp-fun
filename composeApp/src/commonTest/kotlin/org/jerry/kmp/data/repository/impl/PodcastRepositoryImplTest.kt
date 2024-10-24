package org.jerry.kmp.data.repository.impl

import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlinx.coroutines.test.runTest
import assertk.assertThat
import assertk.assertions.*
import io.ktor.client.HttpClient
import org.jerry.kmp.data.remote.dto.PodcastDTO
import org.jerry.kmp.data.response.BasePaginationResponse
import org.jerry.kmp.network.PodcastApi
import org.jerry.kmp.utilities.Resource
import org.jerry.kmp.utilities.ThrowableWithMessage
import kotlin.test.assertEquals

class PodcastRepositoryImplTest {
    private lateinit var api: PodcastApi
    private lateinit var repository: PodcastRepositoryImpl


    private val mockPodcastDTO = PodcastDTO(
        id = 1,
        title = "Test Podcast",
        description = "Test Description",
        author = "Test Author",
        image = "test.jpg",
        link = "https://test.com"
    )

    private val mockPaginationResponse = BasePaginationResponse(
        results = listOf(mockPodcastDTO),
        total = 1,
        limit = 1,
        offset = 10
    )

    @BeforeTest
    fun setup() {
        api = FakePodcastApi()
        repository = PodcastRepositoryImpl(api)
    }

    @Test
    fun `getTopList successfully transforms DTO to domain model`() = runTest {
        // Given
        (api as FakePodcastApi).setResponse(
            Resource.Success(mockPaginationResponse)
        )

        // When
        val result = repository.getTopList()

        // Then
        assertThat(result).isInstanceOf(Resource.Success::class)

        val successResult = result as Resource.Success
        assertEquals(1, successResult.data?.size)
    }

    @Test
    fun `getTopList propagates error from API`() = runTest {
        // Given
        val error = ThrowableWithMessage(Exception("Test error"))
        (api as FakePodcastApi).setResponse(
            Resource.Error(error)
        )

        // When
        val result = repository.getTopList()

        // Then
        assertThat(result).isInstanceOf(Resource.Error::class)
        val errorResult = result as Resource.Error
        assertThat(errorResult.throwableWithMessage).isEqualTo(error)
    }

    @Test
    fun `getTopList handles empty list`() = runTest {
        // Given
        (api as FakePodcastApi).setResponse(
            Resource.Success(
                BasePaginationResponse(
                    results = emptyList(),

                    total = 0,
                    limit = 0,
                    offset = 0
                )
            )
        )

        // When
        val result = repository.getTopList()

        // Then
        assertThat(result).isInstanceOf(Resource.Success::class)
        val successResult = result as Resource.Success

        assertEquals(0, successResult.data?.size )
    }
}

// Fake API implementation for testing
class FakePodcastApi : PodcastApi(HttpClient()) {
    private var mockResponse: Resource<BasePaginationResponse<PodcastDTO>>? = null

    fun setResponse(response: Resource<BasePaginationResponse<PodcastDTO>>) {
        mockResponse = response
    }

    override suspend fun getTopList(): Resource<BasePaginationResponse<PodcastDTO>> {
        return mockResponse ?: throw IllegalStateException("Mock response not set")
    }
}