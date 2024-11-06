package org.jerry.kmp.data.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

fun getDatabaseBuilder(ctx: Context): AppDatabase {
    val dbFile = ctx.getDatabasePath("podcast_app.db")
    return Room.databaseBuilder<AppDatabase>(ctx, dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}