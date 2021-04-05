package com.rosalynbm.wannago.ui.poilist

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rosalynbm.wannago.base.BaseViewModel
import com.rosalynbm.wannago.data.PoiDataSource
import com.rosalynbm.wannago.data.entity.Poi
import com.rosalynbm.wannago.data.entity.Result
import com.rosalynbm.wannago.model.Place
import com.rosalynbm.wannago.model.PoiItem
import com.rosalynbm.wannago.model.Review
import com.rosalynbm.wannago.repository.PlacesRepository
import kotlinx.coroutines.launch

class PoisListViewModel(application: Application,
                        private val dataSource: PoiDataSource,
                        private val placesRepository: PlacesRepository
): BaseViewModel(application) {

    // list that holds the place data to be displayed on the UI
    val poisList = MutableLiveData<List<PoiItem>>()
    val reviewsList = MutableLiveData<List<Review>>()

    /**
     * Get all the reminders from the DataSource and add them to the remindersList to be shown on the UI,
     * or show error if any
     */
    fun loadPlaces() {
        showLoading.value = true
        viewModelScope.launch {
            //interacting with the dataSource has to be through a coroutine
            val result = dataSource.getPois()
            showLoading.postValue(false)
            when (result) {
                is Result.Success<*> -> {
                    val dataList = ArrayList<PoiItem>()
                    dataList.addAll((result.data as List<Poi>).map { poi ->
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

    suspend fun getPlaceDetails(placeId: String): Place {
        return placesRepository.getPlace(placeId) ?: Place()
    }

    fun getPhotoUrl(placeRef: String): String {
        return "https://maps.googleapis.com/maps/api/place/photo?maxwidth=1800&photoreference=" + placeRef + "&key=AIzaSyCZvIpJYq2dgv3QohyvXLeiQMSHfIltgDk"
    }

    fun loadReviews(list: List<Review>) {
        reviewsList.value = list
    }
}