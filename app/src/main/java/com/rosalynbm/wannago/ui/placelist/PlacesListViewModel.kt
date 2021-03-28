package com.rosalynbm.wannago.ui.placelist

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rosalynbm.wannago.base.BaseViewModel
import com.rosalynbm.wannago.data.PlaceDataSource
import com.rosalynbm.wannago.data.dto.PlaceDTO
import com.rosalynbm.wannago.data.dto.Result
import kotlinx.coroutines.launch

class PlacesListViewModel(application: Application,
                          private val dataSource: PlaceDataSource
): BaseViewModel(application) {

    // list that holds the place data to be displayed on the UI
    val placesList = MutableLiveData<List<PlaceItem>>()

    /**
     * Get all the reminders from the DataSource and add them to the remindersList to be shown on the UI,
     * or show error if any
     */
    fun loadPlaces() {
        //showLoading.value = true
        viewModelScope.launch {
            //interacting with the dataSource has to be through a coroutine
            val result = dataSource.getPlaces()
            //showLoading.postValue(false)
            when (result) {
                is Result.Success<*> -> {
                    val dataList = ArrayList<PlaceItem>()
                    dataList.addAll((result.data as List<PlaceDTO>).map { place ->
                        //map the place data from the DB to the be ready to be displayed on the UI
                        PlaceItem(
                            place.location,
                            place.description,
                            place.latitude,
                            place.longitude,
                            place.id
                        )
                    })
                    placesList.value = dataList
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
        showNoData.value = placesList.value == null || placesList.value!!.isEmpty()
    }
}