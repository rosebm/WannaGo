package com.rosalynbm.wannago2.api.dto

import com.rosalynbm.wannago2.model.Photo
import com.rosalynbm.wannago2.data.entity.PlaceEntity
import com.rosalynbm.wannago2.data.entity.ReviewEntity
import com.rosalynbm.wannago2.model.Place
import com.rosalynbm.wannago2.model.Review
import com.squareup.moshi.Json

class JSONPlace(
                @Json(name = "reference")
                val reference: String,
                @Json(name = "formatted_address")
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
                 val reviews: List<JSONReview>
                 ) {

    fun toModel(place_id: String): Place {
        return Place(place_id, reference, formatted_address, icon, name, types, photos,
                rating.toFloat(), convertDtoToModel(reviews))
    }

    fun toEntity(place_id: String): PlaceEntity {
        return PlaceEntity(place_id, reference, formatted_address, icon, name, types, photos,
            rating.toFloat(), convertDtoToEntity(reviews))
    }

    private fun convertDtoToModel(jsonList: List<JSONReview>): List<Review> {
        val list : MutableList<Review> = mutableListOf()
        jsonList.map {
            list.add(it.toReview())
        }

        return list
    }

    private fun convertDtoToEntity(jsonList: List<JSONReview>): List<ReviewEntity> {
        val list : MutableList<ReviewEntity> = mutableListOf()
        jsonList.map {
            list.add(it.toReviewEntity())
        }

        return list
    }

}