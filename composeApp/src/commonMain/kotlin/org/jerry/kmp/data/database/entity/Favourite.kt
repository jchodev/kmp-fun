package org.jerry.kmp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favourites")
data class Favourite(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val podcastId: Long,
    val createdAt: Long,
)