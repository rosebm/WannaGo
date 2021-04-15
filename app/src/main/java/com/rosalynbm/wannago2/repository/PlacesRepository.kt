package com.rosalynbm.wannago2.repository

import com.rosalynbm.wannago2.api.PlaceRemoteDataSource
import com.rosalynbm.wannago2.data.local.PlaceLocalDataSource
import com.rosalynbm.wannago2.model.Place

class PlacesRepository(private val remoteDataSource: PlaceRemoteDataSource,
                       private val localDataSource: PlaceLocalDataSource
) {
    suspend fun getPlace(id: String): Place? {

        if (localDataSource.getPlaceById(id) == null) {
            val remoteData = remoteDataSource.getPlaceById(id)
            remoteData?.let {
                localDataSource.savePlace(remoteData.toEntity(id))
            }
        }

        return localDataSource.getPlaceById(id)
    }

}