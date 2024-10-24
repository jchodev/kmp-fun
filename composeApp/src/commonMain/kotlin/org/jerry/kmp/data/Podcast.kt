package org.jerry.kmp.data

import kotlinx.serialization.Serializable
import org.jerry.kmp.contants.IMAGE_URL
import org.jerry.kmp.data.remote.dto.PodcastDTO

@Serializable
data class Podcast(
    val author: String = "",
    val description: String = "",
    val id: Long = 0,
    val imageUrl: String = "",
    val link: String = "",
    val title: String = "",
)

fun PodcastDTO.toPodcast(): Podcast {
    return Podcast(
        author = author,
        description = description,
        id = id,
        imageUrl = image?.let { IMAGE_URL+it } ?: "",
        link = link ?: "",
        title = title
    )
}

