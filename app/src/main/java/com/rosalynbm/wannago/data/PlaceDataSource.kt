package com.rosalynbm.wannago.data

import com.rosalynbm.wannago.data.dto.PlaceDTO
import com.rosalynbm.wannago.data.dto.Result

interface PlaceDataSource {
    suspend fun getPlaces(): Result<List<PlaceDTO>>
    suspend fun savePlace(place: PlaceDTO)
    suspend fun getPlace(id: String): Result<PlaceDTO>
    suspend fun deleteAllPlace()
}