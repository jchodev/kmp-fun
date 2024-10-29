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

    private val _favourites = MutableStateFlow<List<Favourite>>(emptyList())
    val favourites = _favourites.asStateFlow()

    init {
        getData()
        getFavourites()
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
                            data =  result.data!!
                        )
                    }
                    is Resource.Error -> {
                        _podcastListState.value = podcastListState.value.copy(
                            errorMessage = result.throwableWithMessage!!.displayError()
                        )
                    }
                }
            }
            _podcastListState.value = podcastListState.value.copy(
                isLoading = false
            )
        }
    }


    private fun getFavourites() {
        viewModelScope.launch {
            favouriteRepository.getFavourites().let { favourites ->
                _favourites.value = favourites
            }
        }
    }

    fun toggleFavourite(podcastId: Long) {
        viewModelScope.launch {
            val existingFavourite = _favourites.value.find { it.podcastId == podcastId }
            if (existingFavourite != null) {
                favouriteRepository.deleteFavourite(existingFavourite.id)
            } else {
                favouriteRepository.createFavourite(podcastId)
            }
            getFavourites() // 重新獲取最新收藏列表
        }
    }

    fun isFavourite(podcastId: Long): Boolean {
        return _favourites.value.any { it.podcastId == podcastId }
    }

    fun clearError(){
        _podcastListState.value = podcastListState.value.copy(
            errorMessage = null
        )
    }
}

data class PodcastListState(
    val isLoading: Boolean = false,
    val data: List<Podcast> = emptyList(),
    val errorMessage: String? = null
)