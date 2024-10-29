package org.jerry.kmp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.jerry.kmp.data.database.entity.Favourite

@Dao
interface FavouriteDao {

    @Query("SELECT * FROM favourites")
    suspend fun getAllFavourites(): List<Favourite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createFavourite(favourite: Favourite)

    @Query("DELETE FROM favourites WHERE id = :id")
    suspend fun deleteFavouriteById(id: Long)
}