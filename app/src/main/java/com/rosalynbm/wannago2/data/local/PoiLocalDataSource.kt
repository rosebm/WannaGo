package com.rosalynbm.wannago2.data.local

import com.rosalynbm.wannago2.data.PoiDataSource
import com.rosalynbm.wannago2.data.Result
import com.rosalynbm.wannago2.data.entity.PoiEntity
import com.rosalynbm.wannago2.data.local.dao.PoiDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PoiLocalDataSource(private val poiDao: PoiDao,
                         private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PoiDataSource {

    override suspend fun getPois(): Result<List<PoiEntity>> = withContext(ioDispatcher) {
        try {
            Result.Success(poiDao.getPois())
        } catch (ex: Exception) {
            Result.Error(ex.localizedMessage)
        }
    }

    override suspend fun savePoi(poiEntity: PoiEntity) = withContext(ioDispatcher) {
        poiDao.savePoi(poiEntity)
    }

    override suspend fun getPoi(id: String): Result<PoiEntity> = withContext(ioDispatcher) {
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