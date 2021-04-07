package com.rosalynbm.wannago2.ui.poilist

import com.rosalynbm.wannago2.R
import com.rosalynbm.wannago2.base.BaseRecyclerViewAdapter
import com.rosalynbm.wannago2.model.PoiItem

//Use data binding to show the point of interest on the item
class PoisListAdapter(callBack: (selectedReminder: PoiItem) -> Unit) :
    BaseRecyclerViewAdapter<PoiItem>(callBack) {
    override fun getLayoutRes(viewType: Int) = R.layout.item_poi
}