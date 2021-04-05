package com.rosalynbm.wannago.data

import com.rosalynbm.wannago.data.entity.Poi
import com.rosalynbm.wannago.data.entity.Result

interface PoiDataSource {
    suspend fun getPois(): Result<List<Poi>>
    suspend fun savePoi(poi: Poi)
    suspend fun getPoi(id: String): Result<Poi>
    suspend fun deleteAllPois()
}