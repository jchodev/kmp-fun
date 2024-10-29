package org.jerry.kmp.data.repository.impl

import kotlinx.datetime.Clock
import org.jerry.kmp.data.database.AppDatabase
import org.jerry.kmp.data.database.entity.Favourite
import org.jerry.kmp.data.repository.FavouriteRepository

class FavouriteRepositoryImpl(
    private val appDatabase: AppDatabase
): FavouriteRepository {
    override suspend fun createFavourite(podcastId: Long) {
        appDatabase.getFavouriteDao().createFavourite(favourite = Favourite(
            podcastId = podcastId,
            createdAt = Clock.System.now().toEpochMilliseconds()
        ))
    }

    override suspend fun deleteFavourite(id: Long) {
        appDatabase.getFavouriteDao().deleteFavouriteById(id)
    }

    override suspend fun getFavourites(): List<Favourite> {
        return appDatabase.getFavouriteDao().getAllFavourites()
    }
}