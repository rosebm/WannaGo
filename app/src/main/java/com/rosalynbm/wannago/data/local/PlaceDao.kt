package com.rosalynbm.wannago.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rosalynbm.wannago.data.dto.PlaceDTO

/**
 * Data Access Object for the places table.
 */
@Dao
interface PlaceDao {

    /**
     * @return all places.
     */
    @Query("SELECT * FROM places")
    suspend fun getPlaces(): List<PlaceDTO>

    /**
     * @param placeId the id of the place
     * @return the place object with the placeId
     */
    @Query("SELECT * FROM places where entry_id = :placeId")
    suspend fun getPlaceById(placeId: String): PlaceDTO?

    /**
     * Insert a place in the database. If the place already exists, replace it.
     *
     * @param place the place to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePlace(place: PlaceDTO)

    /**
     * Delete all places.
     */
    @Query("DELETE FROM places")
    suspend fun deleteAllPlaces()
}