package com.rosalynbm.wannago.ui.poilist

import com.rosalynbm.wannago.R
import com.rosalynbm.wannago.base.BaseRecyclerViewAdapter
import com.rosalynbm.wannago.model.PoiItem

//Use data binding to show the point of interest on the item
class PoisListAdapter(callBack: (selectedReminder: PoiItem) -> Unit) :
    BaseRecyclerViewAdapter<PoiItem>(callBack) {
    override fun getLayoutRes(viewType: Int) = R.layout.item_poi
}