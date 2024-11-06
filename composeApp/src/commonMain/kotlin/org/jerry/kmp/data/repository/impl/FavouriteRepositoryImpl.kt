package org.jerry.kmp.data.repository.impl

import kotlinx.datetime.Clock
import org.jerry.kmp.data.database.AppDatabase
import org.jerry.kmp.data.database.dao.FavouriteDao
import org.jerry.kmp.data.database.entity.Favourite
import org.jerry.kmp.data.repository.FavouriteRepository

class FavouriteRepositoryImpl(
    private val dao: FavouriteDao
): FavouriteRepository {
    override suspend fun createFavourite(podcastId: Long) {
        dao.createFavourite(favourite = Favourite(
            podcastId = podcastId,
            createdAt = Clock.System.now().toEpochMilliseconds()
        ))
    }

    override suspend fun deleteFavourite(id: Long) {
        dao.deleteFavouriteById(id)
    }

    override suspend fun getFavourites(): List<Favourite> {
        return dao.getAllFavourites()
    }
}