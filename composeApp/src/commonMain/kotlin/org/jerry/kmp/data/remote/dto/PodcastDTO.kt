package org.jerry.kmp.data.remote.dto

import kotlinx.serialization.Serializable

//maybe need for ref: https://www.kodeco.com/books/kotlin-multiplatform-by-tutorials/v2.0/chapters/11-serialization
@Serializable
data class PodcastDTO(
    val author: String = "",
    val description: String = "",
    val id: Long = 0,
    val image: String? = null,
    val link: String? = null,
    val title: String
)