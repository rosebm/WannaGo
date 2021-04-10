package com.rosalynbm.wannago2.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rosalynbm.wannago2.data.entity.PoiEntity

/**
 * Data Access Object for the places table.
 */
@Dao
interface PoiDao {

    /**
     * @return all Points Of Interests.
     */
    @Query("SELECT * FROM pois")
    suspend fun getPois(): List<PoiEntity>

    /**
     * @param poiId the id of the poi
     * @return the poi object with the poiId
     */
    @Query("SELECT * FROM pois where entry_id = :poiId")
    suspend fun getPoiById(poiId: String): PoiEntity?

    /**
     * Insert a poi in the database. If the poi already exists, replace it.
     *
     * @param poiEntity the point of interest to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePoi(poiEntity: PoiEntity)

    /**
     * Delete all pois.
     */
    @Query("DELETE FROM pois")
    suspend fun deleteAllPois()
}