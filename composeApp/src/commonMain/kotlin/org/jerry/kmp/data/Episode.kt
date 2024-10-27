package org.jerry.kmp.data

import androidx.compose.ui.text.input.KeyboardType.Companion.Uri
import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.thauvin.erik.urlencoder.UrlEncoderUtil
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

val EpisodeType = object : NavType<Episode>(
    isNullableAllowed = false
) {
    override fun put(bundle: Bundle, key: String, value: Episode) {
        bundle.putString(
            key,
            Json.encodeToString(value)
        )
    }
    override fun get(bundle: Bundle, key: String): Episode? {
        val jsonString = bundle.getString(key)
        return if (jsonString != null) {
            Json.decodeFromString<Episode>(jsonString)
        } else {
            null
        }
    }

    override fun parseValue(value: String): Episode {
        // Serialized values must always be Uri encoded
        return Json.decodeFromString<Episode>(value)
    }

    override fun serializeAsValue(value: Episode): String {
        return UrlEncoderUtil.encode(Json.encodeToString(value))
        //return Json.encodeToString(value)
    }
}