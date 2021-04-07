package com.rosalynbm.wannago2.ui.place

import com.rosalynbm.wannago2.R
import com.rosalynbm.wannago2.base.BaseRecyclerViewAdapter
import com.rosalynbm.wannago2.model.Review

/** Use data binding to show the review on the item */
class ReviewListAdapter(callBack: (review: Review) -> Unit) :
    BaseRecyclerViewAdapter<Review>(callBack) {
    override fun getLayoutRes(viewType: Int) = R.layout.item_review
}