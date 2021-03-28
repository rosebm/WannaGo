package com.rosalynbm.wannago.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.PointOfInterest
import com.rosalynbm.wannago.R
import com.rosalynbm.wannago.base.BaseViewModel
import com.rosalynbm.wannago.base.NavigationCommand
import com.rosalynbm.wannago.data.PlaceDataSource
import com.rosalynbm.wannago.data.dto.PlaceDTO
import com.rosalynbm.wannago.ui.placelist.PlaceItem
import kotlinx.coroutines.launch

class MapViewModel(val app: Application,
                   private val dataSource: PlaceDataSource
): BaseViewModel(app) {

    val placeLocationName = MutableLiveData<String>()
    val placeDescription = MutableLiveData<String>()
    val selectedPOI = MutableLiveData<PointOfInterest>()
    val latitude = MutableLiveData<Double>()
    val longitude = MutableLiveData<Double>()

    /**
     * Clear the live data objects to start fresh next time the view model gets called
     */
    fun onClear() {
        placeLocationName.value = null
        placeDescription.value = null
        selectedPOI.value = null
        latitude.value = null
        longitude.value = null
    }

    fun validateAndSaveReminder(reminderData: PlaceItem) {
        if (validateEnteredData(reminderData)) {
            savePlace(reminderData)
        }
    }

    /**
     * Validate the entered data and show error to the user if there's any invalid data
     */
    private fun validateEnteredData(placeItem: PlaceItem): Boolean {

        if (placeItem.location.isNullOrEmpty()) {
            showSnackBarInt.value = R.string.err_select_location
            return false
        }
        return true
    }

    /**
     * Saves the place on local db
     */
    private fun savePlace(placeItem: PlaceItem) {
        showLoading.value = true
        viewModelScope.launch {
            dataSource.savePlace(
                PlaceDTO(
                    placeItem.location,
                    placeItem.description,
                    placeItem.latitude,
                    placeItem.longitude,
                    placeItem.id
                )
            )
            showLoading.value = false
            showToast.value = app.getString(R.string.place_saved)
            navigationCommand.value = NavigationCommand.Back
        }
    }
}