package com.rosalynbm.wannago2.api

import com.rosalynbm.wannago2.api.dto.JSONResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("details/json?fields=formatted_address,icon,name,types,photos,rating,reviews")
    suspend fun getPlaceById(@Query("key")
                            key: String,
                          @Query("place_id")
                          place_id: String
    ): Response<JSONResponse>

}