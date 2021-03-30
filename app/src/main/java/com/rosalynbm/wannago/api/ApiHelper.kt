package com.rosalynbm.wannago.api

class ApiHelper(private val retrofitService: RetrofitService) {

    suspend fun getPlaces() = retrofitService.getPlaces()
}