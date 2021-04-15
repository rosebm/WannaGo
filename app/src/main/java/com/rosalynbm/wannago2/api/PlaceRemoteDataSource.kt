package com.rosalynbm.wannago2.api

import com.rosalynbm.wannago2.api.dto.JSONPlace
import com.rosalynbm.wannago2.model.Place
import com.rosalynbm.wannago2.util.Constants

class PlaceRemoteDataSource(private val retrofitService: RetrofitService) {

    suspend fun getPlaceById(placeId: String): JSONPlace? {
        val resp = retrofitService.getPlaceById(Constants.API_KEY, placeId)

        return if (resp.isSuccessful) {
            resp.body()?.result
        } else
            null
    }

}