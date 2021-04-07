package com.rosalynbm.wannago2.data

import com.rosalynbm.wannago2.data.entity.Poi
import com.rosalynbm.wannago2.data.entity.Result

interface PoiDataSource {
    suspend fun getPois(): Result<List<Poi>>
    suspend fun savePoi(poi: Poi)
    suspend fun getPoi(id: String): Result<Poi>
    suspend fun deleteAllPois()
}