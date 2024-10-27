package org.jerry.kmp.viewmodel.podcastlist

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.jerry.kmp.MockStubs
import org.jerry.kmp.MockStubs.Companion.mockErrorPodcastListResourceDTO
import org.jerry.kmp.data.repository.PodcastRepository
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class PodcastListViewModelFailTest {
    private lateinit var podcastRepository: PodcastRepository
    private lateinit var viewModel: PodcastListViewModel
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        podcastRepository = mock <PodcastRepository> {}
        everySuspend { podcastRepository.getTopList() } returns flowOf(
            mockErrorPodcastListResourceDTO
        )
        viewModel = PodcastListViewModel(podcastRepository)
    }

    @Test
    fun `init triggers loading state and fetches data`() = runTest {
        // When - init is called automatically
        testDispatcher.scheduler.advanceUntilIdle()
        val state = viewModel.podcastListState.first()

        assertEquals(false, state.isLoading)

        assertEquals(true, state.errorMessage != null)
        assertEquals(MockStubs.mockExceptionStr, state.errorMessage)
    }
}