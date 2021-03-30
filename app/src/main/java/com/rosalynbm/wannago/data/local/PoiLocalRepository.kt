package com.rosalynbm.wannago.data.local

import com.rosalynbm.wannago.data.PoiDataSource
import com.rosalynbm.wannago.data.dto.PoiDTO
import com.rosalynbm.wannago.data.dto.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PoiLocalRepository (private val poiDao: PoiDao,
                          private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PoiDataSource {

    override suspend fun getPois(): Result<List<PoiDTO>> = withContext(ioDispatcher) {
        try {
            Result.Success(poiDao.getPois())
        } catch (ex: Exception) {
            Result.Error(ex.localizedMessage)
        }
    }

    override suspend fun savePoi(poi: PoiDTO) = withContext(ioDispatcher) {
        poiDao.savePoi(poi)
    }

    override suspend fun getPoi(id: String): Result<PoiDTO>
            = withContext(ioDispatcher) {
        try {
            val poi = poiDao.getPoiById(id)
            if (poi != null)
                return@withContext Result.Success(poi)
            else return@withContext Result.Error("Point Of Interest not found!")
        } catch (ex: Exception) {
            return@withContext Result.Error(ex.localizedMessage)
        }
    }

    override suspend fun deleteAllPois() {
        withContext(ioDispatcher) {
            poiDao.deleteAllPois()
        }
    }
}