package com.rosalynbm.wannago.ui.placelist

import com.rosalynbm.wannago.R
import com.rosalynbm.wannago.base.BaseRecyclerViewAdapter

//Use data binding to show the reminder on the item
class PlacesListAdapter(callBack: (selectedReminder: PlaceItem) -> Unit) :
    BaseRecyclerViewAdapter<PlaceItem>(callBack) {
    override fun getLayoutRes(viewType: Int) = R.layout.item_place
}