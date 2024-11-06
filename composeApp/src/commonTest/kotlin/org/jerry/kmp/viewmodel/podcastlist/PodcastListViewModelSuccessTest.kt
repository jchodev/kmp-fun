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
import org.jerry.kmp.MockStubs.Companion.mockPodcastDTOS
import org.jerry.kmp.MockStubs.Companion.mockSuccessPodcastListResourceDTO
import org.jerry.kmp.data.repository.FavouriteRepository
import org.jerry.kmp.data.repository.PodcastRepository
import org.jerry.kmp.data.toPodcast
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class PodcastListViewModelSuccessTest {
    private lateinit var podcastRepository: PodcastRepository
    private lateinit var favouriteRepository: FavouriteRepository
    private lateinit var viewModel: PodcastListViewModel
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        podcastRepository = mock <PodcastRepository> {}
        favouriteRepository = mock <FavouriteRepository> {}
        everySuspend { podcastRepository.getTopList() } returns flowOf(
            mockSuccessPodcastListResourceDTO
        )
        everySuspend { favouriteRepository.getFavourites() } returns MockStubs.mockFavourites
        viewModel = PodcastListViewModel(
            podcastRepository = podcastRepository,
            favouriteRepository = favouriteRepository,
        )
    }

    @Test
    fun `init triggers loading state and fetches data`() = runTest {
        // When - init is called automatically
        testDispatcher.scheduler.advanceUntilIdle()
        val state = viewModel.podcastListState.first()
        assertEquals(false, state.isLoading)
        assertEquals(mockPodcastDTOS.map { it.toPodcast() }, state.podcasts)
        assertEquals(null, state.errorMessage)
        assertEquals(MockStubs.mockFavourites, state.favourites)
    }
}