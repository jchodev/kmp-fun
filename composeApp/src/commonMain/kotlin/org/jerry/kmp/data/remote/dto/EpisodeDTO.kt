package org.jerry.kmp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDTO (
    val description: String? = null,
    val duration: Long? = 0,

    val id: Long? = null,
    val mimeType: String? = null,
    val published: Long? = null,

    val title: String? = null,
    val type: String? = null,
    val url: String? = null,
)