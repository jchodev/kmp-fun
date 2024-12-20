package org.jerry.kmp.viewmodel.podcastlist

import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
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
import org.jerry.kmp.MockStubs.Companion.mockSuccessPodcastListResourceDTO
import org.jerry.kmp.data.repository.FavouriteRepository
import org.jerry.kmp.data.repository.PodcastRepository
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class PodcastListViewModelFailTest {
    private lateinit var podcastRepository: PodcastRepository
    private lateinit var favouriteRepository: FavouriteRepository
    private lateinit var viewModel: PodcastListViewModel
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        podcastRepository = mock <PodcastRepository> {}
        favouriteRepository = mock <FavouriteRepository> {}

    }

    @Test
    fun testFetchPodcastListFromServerError() = runTest {
        everySuspend { podcastRepository.getTopList() } returns flowOf(
            mockErrorPodcastListResourceDTO
        )
        everySuspend { favouriteRepository.getFavourites() } returns MockStubs.mockFavourites
        viewModel = PodcastListViewModel(
            podcastRepository = podcastRepository,
            favouriteRepository = favouriteRepository,
        )

        // When - init is called automatically
        testDispatcher.scheduler.advanceUntilIdle()
        val state = viewModel.podcastListState.first()

        assertEquals(false, state.isLoading)

        assertEquals(true, state.errorMessage != null)
        assertEquals(MockStubs.mockExceptionStr, state.errorMessage)
    }

    @Test
    fun testFetchFavouritesError() = runTest {
        everySuspend { podcastRepository.getTopList() } returns flowOf(
            mockSuccessPodcastListResourceDTO
        )
        everySuspend { favouriteRepository.getFavourites() } throws MockStubs.mockNormalException
        viewModel = PodcastListViewModel(
            podcastRepository = podcastRepository,
            favouriteRepository = favouriteRepository,
        )

        // When - init is called automatically
        testDispatcher.scheduler.advanceUntilIdle()
        val state = viewModel.podcastListState.first()

        assertEquals(false, state.isLoading)
        assertEquals(true, state.favouriteError != null)
    }
}