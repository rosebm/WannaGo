package com.rosalynbm.wannago.data.local

import com.rosalynbm.wannago.data.PlaceDataSource
import com.rosalynbm.wannago.data.dto.PlaceDTO
import com.rosalynbm.wannago.data.dto.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlacesLocalRepository (private val placeDao: PlaceDao,
private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PlaceDataSource {

    override suspend fun getPlaces(): Result<List<PlaceDTO>> = withContext(ioDispatcher) {
        try {
            Result.Success(placeDao.getPlaces())
        } catch (ex: Exception) {
            Result.Error(ex.localizedMessage)
        }
    }

    override suspend fun savePlace(place: PlaceDTO) = withContext(ioDispatcher) {
        placeDao.savePlace(place)
    }

    override suspend fun getPlace(id: String): Result<PlaceDTO>
            = withContext(ioDispatcher) {
        try {
            val place = placeDao.getPlaceById(id)
            if (place != null)
                return@withContext Result.Success(place)
            else return@withContext Result.Error("Place not found!")
        } catch (ex: Exception) {
            return@withContext Result.Error(ex.localizedMessage)
        }
    }

    override suspend fun deleteAllPlace() {
        withContext(ioDispatcher) {
            placeDao.deleteAllPlaces()
        }
    }
}