package org.jerry.kmp.data

import kotlinx.serialization.Serializable
import org.jerry.kmp.data.remote.dto.EpisodeDTO

@Serializable
data class Episode(
    val description: String = "",
    val duration: Long = 0,

    val id: Long = 0,
    val mimeType: String = "",
    val published: Long = 0,

    val title: String = "",
    val type: String = "",
    val url: String = "",

    val podcast: Podcast = Podcast(),
)

fun EpisodeDTO.toEpisode():Episode {
    return Episode(
        description = description ?: "",
        duration = duration ?: 0,
        id = id ?: 0,
        mimeType = mimeType ?: "",
        title = title ?: "",
        type = type ?: "",
        url = url ?: "",
        published = published ?: 0,
    )
}