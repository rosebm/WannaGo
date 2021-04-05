package com.rosalynbm.wannago.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rosalynbm.wannago.data.entity.Poi

/**
 * The Room Database that contains the pois table.
 */
@Database(entities = [Poi::class], version = 1, exportSchema = false)
abstract class PoiDatabase : RoomDatabase() {

    abstract fun poiDao(): PoiDao
}