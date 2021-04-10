package com.rosalynbm.wannago2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Place(
    val place_id: String = "",
    val reference: String = "",
    val formatted_address: String = "",
    val icon: String = "",
    val name: String = "",
    val types: List<String>? = emptyList(),
    val photos: List<Photo>?,
    val rating: Float? = null,
    val reviews: List<Review>? = null
): Parcelable