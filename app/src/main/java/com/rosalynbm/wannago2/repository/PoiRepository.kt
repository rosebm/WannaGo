package com.rosalynbm.wannago2.repository

import com.rosalynbm.wannago2.data.entity.PoiEntity
import com.rosalynbm.wannago2.data.local.PoiLocalDataSource

class PoiRepository(private val localDataSource: PoiLocalDataSource) {

    suspend fun getPois() = localDataSource.getPois()

    suspend fun savePoi(entity: PoiEntity) = localDataSource.savePoi(entity)

    suspend fun getPoi(id: String) = localDataSource.getPoi(id)

    suspend fun deleteAllPois() = localDataSource.deleteAllPois()

}