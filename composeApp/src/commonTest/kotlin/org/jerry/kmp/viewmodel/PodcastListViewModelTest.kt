//package org.jerry.kmp.viewmodel
//
//import kotlin.test.Test
//import kotlin.test.BeforeTest
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.test.*
//import assertk.assertThat
//import assertk.assertions.*
//import kotlinx.coroutines.flow.first
//import org.jerry.kmp.data.Podcast
//import org.jerry.kmp.data.repository.PodcastRepository
//import org.jerry.kmp.utilities.Resource
//import org.jerry.kmp.utilities.ThrowableWithMessage
//import kotlin.test.assertEquals
//
// maybe used https://medium.com/@huctor/kotlin-multiplatform-unit-tests-with-mocks-9f8f3229e6d7
//@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
//class PodcastListViewModelTest {
//    private lateinit var repository: FakePodcastRepository
//    private lateinit var viewModel: PodcastListViewModel
//    private val testDispatcher = StandardTestDispatcher()
//
//    @BeforeTest
//    fun setup() {
//        Dispatchers.setMain(testDispatcher)
//        repository = FakePodcastRepository()
//        viewModel = PodcastListViewModel(repository)
//    }
//
//    @Test
//    fun `init triggers loading state and fetches data`() = runTest {
//        // Given
//        val mockPodcasts = listOf(
//            Podcast(
//                id = 1,
//                title = "Test Podcast",
//                description = "Test Description",
//                author = "Test Author",
//                imageUrl = "test.jpg",
//                link = "https://test.com"
//            )
//        )
//        repository.setResponse(Resource.Success(mockPodcasts))
//
//        // When - init is called automatically
//        testDispatcher.scheduler.advanceUntilIdle()
//
//        // Then
//        val state = viewModel.podcastListState.first()
//        assertEquals(false, state.isLoading)
//        assertEquals(mockPodcasts, state.data)
//        assertEquals(null, state.errorMessage)
//    }
//
//    @Test
//    fun `getData shows loading state during fetch`() = runTest {
//        // Given
//        repository.setResponse(Resource.Success(emptyList()))
//
//        // When
//        viewModel.getData()
//
//        // Then - Check loading state
//        val loadingState = viewModel.podcastListState.first()
//        assertThat(loadingState.isLoading).isTrue()
//
//        // Complete the coroutine
//        testDispatcher.scheduler.advanceUntilIdle()
//
//        // Then - Check final state
//        val finalState = viewModel.podcastListState.first()
//        assertThat(finalState.isLoading).isFalse()
//    }
//
//    @Test
//    fun `getData handles error state`() = runTest {
//        // Given
//        val errorMessage = "Network error"
//        repository.setResponse(
//            Resource.Error(
//                ThrowableWithMessage(
//                    Exception(errorMessage)
//                )
//            )
//        )
//
//        // When
//        viewModel.getData()
//        testDispatcher.scheduler.advanceUntilIdle()
//
//        // Then
//        val state = viewModel.podcastListState.first()
//        assertEquals(false, state.isLoading)
//        assertEquals(0, state.data.size)
//        assertEquals(errorMessage, state.errorMessage)
//    }
//
//    @Test
//    fun `clearError clears error message`() = runTest {
//        // Given
//        repository.setResponse(
//            Resource.Error(
//                ThrowableWithMessage(
//                    Exception("Test error")
//                )
//            )
//        )
//        viewModel.getData()
//        testDispatcher.scheduler.advanceUntilIdle()
//
//        // When
//        viewModel.clearError()
//
//        // Then
//        val state = viewModel.podcastListState.first()
//        assertThat(state.errorMessage).isNull()
//    }
//
//    @Test
//    fun `getData refreshes data on subsequent calls`() = runTest {
//        // Given
//        val initialPodcasts = listOf(
//            Podcast(id = 1, title = "Initial", description = "desc", author = "author", imageUrl = "img", link = "link")
//        )
//        val updatedPodcasts = listOf(
//            Podcast(id = 2, title = "Updated", description = "desc", author = "author", imageUrl = "img", link = "link")
//        )
//
//        repository.setResponse(Resource.Success(initialPodcasts))
//        viewModel.getData()
//        testDispatcher.scheduler.advanceUntilIdle()
//
//        // When
//        repository.setResponse(Resource.Success(updatedPodcasts))
//        viewModel.getData()
//        testDispatcher.scheduler.advanceUntilIdle()
//
//        // Then
//        val state = viewModel.podcastListState.first()
//        assertThat(state.data).isEqualTo(updatedPodcasts)
//    }
//}
//
//// Fake Repository for testing
//class FakePodcastRepository : PodcastRepository {
//    private var mockResponse: Resource<List<Podcast>>? = null
//
//    fun setResponse(response: Resource<List<Podcast>>) {
//        mockResponse = response
//    }
//
//    override suspend fun getTopList(): Resource<List<Podcast>> {
//        return mockResponse ?: throw IllegalStateException("Mock response not set")
//    }
//}