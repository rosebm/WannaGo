package com.rosalynbm.wannago2.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rosalynbm.wannago2.data.entity.PlaceEntity

@Dao
interface PlaceDao {

    @Query("SELECT * FROM place WHERE place_id = :placeId")
    suspend fun getPlaceById(placeId: String): PlaceEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePlace(place: PlaceEntity)
}