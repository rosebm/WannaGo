package com.rosalynbm.wannago2.ui.place

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.rosalynbm.wannago2.base.BaseViewModel
import com.rosalynbm.wannago2.model.Place
import com.rosalynbm.wannago2.model.Review
import com.rosalynbm.wannago2.repository.PlacesRepository
import com.rosalynbm.wannago2.repository.PoiRepository
import com.rosalynbm.wannago2.util.Constants

class PlaceViewModel(application: Application,
                     private val placesRepository: PlacesRepository
): BaseViewModel(application) {

    val reviewsList = MutableLiveData<List<Review>>()

    fun loadReviews(list: List<Review>) {
        reviewsList.value = list
        //invalidateShowNoData()
    }

    fun getPhotoUrl(placeRef: String): String {
        return Constants.BASE_URL + "photo?maxwidth=1800&photoreference="+
                placeRef + "&key=" + Constants.API_KEY
    }

    suspend fun getPlaceDetails(placeId: String): Place? {
        return placesRepository.getPlace(placeId)
    }
}