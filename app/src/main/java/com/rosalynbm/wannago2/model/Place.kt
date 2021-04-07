package com.rosalynbm.wannago2.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "place_tb")
data class Place(
        val formatted_address: String = "",
        val icon: String = "",
        val name: String = "",
        val types: List<String>? = emptyList(),
        val photos: List<Photo>? = null,
        val rating: Float? = null,
        val reviews: List<Review>? = null
): Parcelable