package org.jerry.kmp.data.database

import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import platform.Foundation.NSHomeDirectory
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.IO

fun getDatabaseBuilder(): AppDatabase {
    val dbFile = "${NSHomeDirectory()}/mrx_note.db"
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile,
        factory = { AppDatabase::class.instantiateImpl() }
    ).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}