package com.rosalynbm.wannago.api

import com.rosalynbm.wannago.model.Place
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("place/details/json?")
    suspend fun getPlaces(): Response<List<Place>>
}