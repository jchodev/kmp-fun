package org.jerry.kmp.viewmodel.podcastlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jerry.kmp.data.Podcast
import org.jerry.kmp.data.repository.PodcastRepository
import org.jerry.kmp.utilities.Resource

class PodcastListViewModel(
    private val podcastRepository: PodcastRepository,
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