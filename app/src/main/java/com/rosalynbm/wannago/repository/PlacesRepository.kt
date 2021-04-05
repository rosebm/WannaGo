package com.rosalynbm.wannago.repository

import com.rosalynbm.wannago.api.PlaceRemoteDataSource
import com.rosalynbm.wannago.model.Place

class PlacesRepository(private val placeRemoteDataSource: PlaceRemoteDataSource) {

    //If localDataSource is empty, call Remote one
    suspend fun getPlace(placeId: String): Place? = placeRemoteDataSource.getPlaceById(placeId)

}