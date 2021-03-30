package com.rosalynbm.wannago.data

import com.rosalynbm.wannago.data.dto.PoiDTO
import com.rosalynbm.wannago.data.dto.Result

interface PoiDataSource {
    suspend fun getPois(): Result<List<PoiDTO>>
    suspend fun savePoi(poi: PoiDTO)
    suspend fun getPoi(id: String): Result<PoiDTO>
    suspend fun deleteAllPois()
}