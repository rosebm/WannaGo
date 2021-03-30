package com.rosalynbm.wannago.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rosalynbm.wannago.data.dto.PoiDTO

/**
 * Data Access Object for the places table.
 */
@Dao
interface PoiDao {

    /**
     * @return all Points Of Interests.
     */
    @Query("SELECT * FROM pois")
    suspend fun getPois(): List<PoiDTO>

    /**
     * @param poiId the id of the poi
     * @return the poi object with the poiId
     */
    @Query("SELECT * FROM pois where entry_id = :poiId")
    suspend fun getPoiById(poiId: String): PoiDTO?

    /**
     * Insert a poi in the database. If the poi already exists, replace it.
     *
     * @param poi the point of interest to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePoi(poi: PoiDTO)

    /**
     * Delete all pois.
     */
    @Query("DELETE FROM pois")
    suspend fun deleteAllPois()
}