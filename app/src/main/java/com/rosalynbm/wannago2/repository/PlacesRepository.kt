package com.rosalynbm.wannago2.repository

import com.rosalynbm.wannago2.api.PlaceRemoteDataSource
import com.rosalynbm.wannago2.model.Place

class PlacesRepository(private val placeRemoteDataSource: PlaceRemoteDataSource) {

    //If localDataSource is empty, call Remote one
    suspend fun getPlace(placeId: String): Place? = placeRemoteDataSource.getPlaceById(placeId)

}