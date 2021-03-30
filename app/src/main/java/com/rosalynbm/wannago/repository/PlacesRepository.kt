package com.rosalynbm.wannago.repository

import com.rosalynbm.wannago.api.ApiHelper

class PlacesRepository(private val apiHelper: ApiHelper) {

    suspend fun getPlace() = apiHelper.getPlaces()
}