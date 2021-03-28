package com.rosalynbm.wannago.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rosalynbm.wannago.data.dto.PlaceDTO

/**
 * The Room Database that contains the places table.
 */
@Database(entities = [PlaceDTO::class], version = 1, exportSchema = false)
abstract class PlaceDatabase : RoomDatabase() {

    abstract fun placeDao(): PlaceDao
}