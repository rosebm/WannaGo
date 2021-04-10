package com.rosalynbm.wannago2.data

import com.rosalynbm.wannago2.data.entity.PoiEntity

interface PoiDataSource {
    suspend fun getPois(): Result<List<PoiEntity>>
    suspend fun savePoi(poiEntity: PoiEntity)
    suspend fun getPoi(id: String): Result<PoiEntity>
    suspend fun deleteAllPois()
}