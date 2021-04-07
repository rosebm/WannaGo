package com.rosalynbm.wannago2.ui.map

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.PointOfInterest
import com.rosalynbm.wannago2.R
import com.rosalynbm.wannago2.base.BaseViewModel
import com.rosalynbm.wannago2.base.NavigationCommand
import com.rosalynbm.wannago2.data.PoiDataSource
import com.rosalynbm.wannago2.data.entity.Poi
import com.rosalynbm.wannago2.model.PoiItem
import kotlinx.coroutines.launch

class MapViewModel(val app: Application,
                   val dataSource: PoiDataSource
): BaseViewModel(app) {

    val placeLocationName = MutableLiveData<String>()
    val placeDescription = MutableLiveData<String>()
    val selectedPOI = MutableLiveData<PointOfInterest>()
    val latitude = MutableLiveData<Double>()
    val longitude = MutableLiveData<Double>()
    val placeId = MutableLiveData<String>()

    /**
     * Clear the live data objects to start fresh next time the view model gets called
     */
    fun onClear() {
        placeLocationName.value = null
        placeDescription.value = null
        selectedPOI.value = null
        latitude.value = null
        longitude.value = null
        placeId.value = null
    }

    fun validateAndSaveReminder(reminderData: PoiItem) {
        if (validateEnteredData(reminderData)) {
            savePlace(reminderData)
        }
    }

    /**
     * Validate the entered data and show error to the user if there's any invalid data
     */
    private fun validateEnteredData(poiItem: PoiItem): Boolean {

        if (poiItem.location.isNullOrEmpty()) {
            showSnackBarInt.value = R.string.err_select_location
            return false
        }
        return true
    }

    /**
     * Saves the place on local db, and
     * returns to list screen
     */
    private fun savePlace(poiItem: PoiItem) {
        showLoading.value = true
        viewModelScope.launch {
            dataSource.savePoi(
                Poi(
                    poiItem.location,
                    poiItem.description,
                    poiItem.latitude,
                    poiItem.longitude,
                    poiItem.placeId,
                    poiItem.id
                )
            )
            showLoading.value = false
            showToast.value = app.getString(R.string.place_saved)
            navigationCommand.value = NavigationCommand.Back
        }
    }
}