package com.rosalynbm.wannago.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rosalynbm.wannago.data.dto.PoiDTO

/**
 * The Room Database that contains the pois table.
 */
@Database(entities = [PoiDTO::class], version = 1, exportSchema = false)
abstract class PoiDatabase : RoomDatabase() {

    abstract fun poiDao(): PoiDao
}