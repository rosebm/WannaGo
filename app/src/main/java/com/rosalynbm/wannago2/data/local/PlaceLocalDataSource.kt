package com.rosalynbm.wannago2.data.local

import com.rosalynbm.wannago2.data.entity.PlaceEntity
import com.rosalynbm.wannago2.data.local.dao.PlaceDao
import com.rosalynbm.wannago2.model.Place

class PlaceLocalDataSource(private val dao: PlaceDao) {

    suspend fun getPlaceById(id: String): Place? {
        dao.getPlaceById(id)?.let {
            return it.toModel()
        }

        return null
    }

    suspend fun savePlace(place: PlaceEntity) {
        dao.savePlace(place)
    }
}