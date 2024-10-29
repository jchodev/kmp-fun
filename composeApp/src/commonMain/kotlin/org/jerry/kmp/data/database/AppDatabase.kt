package org.jerry.kmp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.jerry.kmp.data.database.dao.FavouriteDao
import org.jerry.kmp.data.database.entity.Favourite

@Database(entities = [Favourite::class], version = 1)
abstract class AppDatabase : RoomDatabase(), DB {
    abstract fun getFavouriteDao(): FavouriteDao
    override fun clearAllTables(): Unit {}
}

interface DB {
    fun clearAllTables(): Unit {}
}