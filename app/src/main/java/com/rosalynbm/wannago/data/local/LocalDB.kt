package com.rosalynbm.wannago.data.local

import android.content.Context
import androidx.room.Room

/**
 * Singleton class that is used to create a place db
 */
object LocalDB {

    /**
     * static method that creates a place class and returns the DAO of the reminder
     */
    fun createPlacesDao(context: Context): PoiDao {
        return Room.databaseBuilder(
            context.applicationContext,
            PoiDatabase::class.java, "pois.db"
        ).build().poiDao()
    }

}