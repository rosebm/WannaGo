package com.rosalynbm.wannago2.api

import com.rosalynbm.wannago2.model.Place
import com.rosalynbm.wannago2.util.Constants
import com.rosalynbm.wannago2.util.Utils

class PlaceRemoteDataSource(private val retrofitService: RetrofitService) {

    suspend fun getPlaceById(placeId: String): Place? {
        val x = retrofitService.getPlaceById(Constants.API_KEY, placeId)

        return if (x.isSuccessful) {
            x.body()?.result?.toPlace()
        } else
            null
    }

}