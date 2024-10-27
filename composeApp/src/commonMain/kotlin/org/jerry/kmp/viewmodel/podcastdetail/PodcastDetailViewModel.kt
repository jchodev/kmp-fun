package org.jerry.kmp.viewmodel.podcastdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jerry.kmp.data.Episode
import org.jerry.kmp.data.Podcast
import org.jerry.kmp.data.repository.EpisodeRepository

import org.jerry.kmp.utilities.Resource

class PodcastDetailViewModel(
    private val episodeRepository: EpisodeRepository,
): ViewModel() {
    private val _podcastDetailState = MutableStateFlow(PodcastDetailState())
    val podcastDetailState = _podcastDetailState.asStateFlow()

    fun getPodcastsByPodcast(podcast: Podcast) {
        viewModelScope.launch {
            _podcastDetailState.value = podcastDetailState.value.copy(
                errorMessage = null,
                isLoading = true
            )
            episodeRepository.getPodcastsByPodcastId(podcast.id).collectLatest {result ->
                when (result) {
                    is Resource.Success -> {
                        _podcastDetailState.value = podcastDetailState.value.copy(
                            data =  result.data!!
                        )
                    }
                    is Resource.Error -> {
                        _podcastDetailState.value = podcastDetailState.value.copy(
                            errorMessage = result.throwableWithMessage!!.displayError()
                        )
                    }
                }
            }
            _podcastDetailState.value = podcastDetailState.value.copy(
                isLoading = false
            )
        }
    }

    fun clearError(){
        _podcastDetailState.value = _podcastDetailState.value.copy(
            errorMessage = null
        )
    }
}

data class PodcastDetailState(
    val isLoading: Boolean = false,
    val data: List<Episode> = emptyList(),
    val errorMessage: String? = null
)