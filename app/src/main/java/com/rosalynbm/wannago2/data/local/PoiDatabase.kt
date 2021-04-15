package com.rosalynbm.wannago2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rosalynbm.wannago2.data.entity.PlaceEntity
import com.rosalynbm.wannago2.data.entity.PoiEntity
import com.rosalynbm.wannago2.data.local.dao.PlaceDao
import com.rosalynbm.wannago2.data.local.dao.PoiDao

/**
 * The Room Database that contains the pois table.
 */
@Database(entities = [PoiEntity::class, PlaceEntity::class], version = 2, exportSchema = false)
abstract class PoiDatabase : RoomDatabase() {

    abstract fun poiDao(): PoiDao

    abstract fun placeDao(): PlaceDao
}