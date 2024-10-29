package org.jerry.kmp.data.repository

import org.jerry.kmp.data.database.entity.Favourite

interface FavouriteRepository {
    suspend fun createFavourite(podcastId: Long)
    suspend fun deleteFavourite(id: Long)
    suspend fun getFavourites(): List<Favourite>
}