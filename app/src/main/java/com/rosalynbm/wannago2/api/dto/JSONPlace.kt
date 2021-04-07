package com.rosalynbm.wannago2.api.dto

import com.rosalynbm.wannago2.model.Photo
import com.rosalynbm.wannago2.model.Review
import com.rosalynbm.wannago2.model.Place
import com.squareup.moshi.Json

class JSONPlace(@Json(name = "formatted_address")
                 val formatted_address: String,
                @Json(name = "icon")
                 val icon: String,
                @Json(name = "name")
                 val name: String,
                @Json(name = "types")
                val types: List<String>,
                @Json(name = "photos")
                 val photos: List<Photo>,
                @Json(name = "rating")
                 val rating: Double,
                @Json(name = "reviews")
                 val reviews: List<Review>
                 ) {

    fun toPlace(): Place {
        return Place(formatted_address, icon, name, types, photos, rating.toFloat(), reviews)
    }
}