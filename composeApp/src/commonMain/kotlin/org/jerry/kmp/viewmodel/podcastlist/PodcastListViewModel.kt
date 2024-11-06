package org.jerry.kmp.viewmodel.podcastlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jerry.kmp.data.Podcast
import org.jerry.kmp.data.database.entity.Favourite
import org.jerry.kmp.data.repository.FavouriteRepository
import org.jerry.kmp.data.repository.PodcastRepository
import org.jerry.kmp.utilities.Resource

class PodcastListViewModel(
    private val podcastRepository: PodcastRepository,
    private val favouriteRepository: FavouriteRepository,
): ViewModel() {

    private val _podcastListState = MutableStateFlow(PodcastListState())
    val podcastListState = _podcastListState.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        clearError()
        viewModelScope.launch {
            _podcastListState.value = podcastListState.value.copy(
                isLoading = true
            )
            podcastRepository.getTopList().collectLatest { result->
                when (result) {
                    is Resource.Success -> {
                        _podcastListState.value = podcastListState.value.copy(
                            podcasts =  result.data!!
                        )
                    }
                    is Resource.Error -> {
                        _podcastListState.value = podcastListState.value.copy(
                            errorMessage = result.throwableWithMessage!!.displayError()
                        )
                    }
                }
            }
            getFavourites()
            _podcastListState.value = podcastListState.value.copy(
                isLoading = false
            )
        }
    }

    private suspend fun getFavourites() {
        try {
            favouriteRepository.getFavourites().let { favourites ->
                _podcastListState.value = podcastListState.value.copy(
                    favourites =  favourites
                )
            }
        } catch (e: Exception) {
            _podcastListState.value = podcastListState.value.copy(
                favouriteError = e.message ?: "Get Favourites Error(Error code:100), please connect customer service",
            )
        }
    }

    fun toggleFavourite(podcastId: Long) {
        viewModelScope.launch {
            val existingFavourite = podcastListState.value.favourites.find { it.podcastId == podcastId }
            if (existingFavourite != null) {
                favouriteRepository.deleteFavourite(existingFavourite.id)
            } else {
                favouriteRepository.createFavourite(podcastId)
            }
            getFavourites()
        }
    }


    fun clearError(){
        _podcastListState.value = podcastListState.value.copy(
            errorMessage = null
        )
    }
}

data class PodcastListState(
    val isLoading: Boolean = false,
    val podcasts: List<Podcast> = emptyList(),
    val errorMessage: String? = null,

    //for favourite
    val favourites: List<Favourite> = emptyList(),
    val favouriteError: String? = null
)