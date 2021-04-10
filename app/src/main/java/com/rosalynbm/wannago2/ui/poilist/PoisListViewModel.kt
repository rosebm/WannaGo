package com.rosalynbm.wannago2.ui.poilist

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rosalynbm.wannago2.base.BaseViewModel
import com.rosalynbm.wannago2.data.entity.PoiEntity
import com.rosalynbm.wannago2.data.Result
import com.rosalynbm.wannago2.model.Place
import com.rosalynbm.wannago2.model.PoiItem
import com.rosalynbm.wannago2.model.Review
import com.rosalynbm.wannago2.repository.PlacesRepository
import com.rosalynbm.wannago2.repository.PoiRepository
import com.rosalynbm.wannago2.util.Constants
import kotlinx.coroutines.launch

class PoisListViewModel(application: Application,
                        private val poiRepository: PoiRepository,
                        private val placesRepository: PlacesRepository
): BaseViewModel(application) {

    // list that holds the place data to be displayed on the UI
    val poisList = MutableLiveData<List<PoiItem>>()
    val reviewsList = MutableLiveData<List<Review>>()

    /**
     * Get all the reminders from the DataSource and add them to the
     * remindersList to be shown on the UI, or show error if any
     */
    fun loadPlaces() {
        showLoading.value = true
        viewModelScope.launch {
            //interacting with the dataSource has to be through a coroutine
            val result = poiRepository.getPois()
            showLoading.postValue(false)
            when (result) {
                is Result.Success<*> -> {
                    val dataList = ArrayList<PoiItem>()
                    dataList.addAll((result.data as List<PoiEntity>).map { poi ->
                        //map the place data from the DB to the be ready to be displayed on the UI
                        PoiItem(
                            poi.location,
                            poi.description,
                            poi.latitude,
                            poi.longitude,
                            poi.placeId,
                            poi.id
                        )
                    })
                    poisList.value = dataList
                }
                is Result.Error ->
                    showSnackBar.value = result.message
            }

            //check if no data has to be shown
            invalidateShowNoData()
        }
    }

    /**
     * Inform the user that there's not any data if placesList is empty
     */
    private fun invalidateShowNoData() {
        showNoData.value = poisList.value == null || poisList.value!!.isEmpty()
    }

    suspend fun getPlaceDetails(placeId: String): Place? {
        return placesRepository.getPlace(placeId)
    }

    fun getPhotoUrl(placeRef: String): String {
        return Constants.BASE_URL + "photo?maxwidth=1800&photoreference="+
                placeRef + "&key=" + Constants.API_KEY
    }

    fun loadReviews(list: List<Review>) {
        reviewsList.postValue(list)
        invalidateShowNoData()
    }

}