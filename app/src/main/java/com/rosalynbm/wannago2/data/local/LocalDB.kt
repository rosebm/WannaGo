package com.rosalynbm.wannago2.data.local

import android.content.Context
import androidx.room.Room
import com.rosalynbm.wannago2.data.local.dao.PoiDao
import com.rosalynbm.wannago2.util.Constants

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
            PoiDatabase::class.java, Constants.DATABASE_NAME
        ).build().poiDao()
    }

}