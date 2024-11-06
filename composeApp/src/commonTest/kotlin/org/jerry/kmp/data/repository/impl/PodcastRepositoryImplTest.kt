package org.jerry.kmp.data.repository.impl

import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlinx.coroutines.test.runTest
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.flow.toList
import org.jerry.kmp.MockStubs
import org.jerry.kmp.network.PodcastApi
import org.jerry.kmp.utilities.Resource
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PodcastRepositoryImplTest {
    private lateinit var api: PodcastApi
    private lateinit var repository: PodcastRepositoryImpl

    @BeforeTest
    fun setup() {
        api = mock<PodcastApi> {}
        repository = PodcastRepositoryImpl(api)
    }

    @Test
    fun testGetTopListSuccessfully() = runTest {
        //Given
        everySuspend { api.getTopList() } returns MockStubs.mockSuccessPodcastListResponse

        // When
        val result = repository.getTopList().toList()
        val actual = result[0]
        // Then
        assertTrue(
            actual is Resource.Success
        )
        assertEquals(MockStubs.mockPodcastDTOS.size, actual.data!!.size)
    }

    @Test
    fun testGetTopListError() = runTest {
        //Given
        everySuspend { api.getTopList() } throws MockStubs.mockNormalException

        // When
        val result = repository.getTopList().toList()
        val actual = result[0]
        // Then
        assertTrue(
            actual is Resource.Error
        )

    }
}
