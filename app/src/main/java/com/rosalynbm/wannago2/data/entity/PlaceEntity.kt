package com.rosalynbm.wannago2.data.entity

import androidx.room.*
import com.rosalynbm.wannago2.data.Converter
import com.rosalynbm.wannago2.model.Photo
import com.rosalynbm.wannago2.model.Place
import com.rosalynbm.wannago2.model.Review

@Entity(tableName = "place")
@TypeConverters(Converter::class)
data class PlaceEntity(
        @PrimaryKey
        @ColumnInfo(name = "place_id")
        var placeId: String,
        @ColumnInfo(name = "reference")
        var reference: String,
        @ColumnInfo(name = "formatted_address")
        val formatted_address: String = "",
        @ColumnInfo(name = "icon")
        val icon: String = "",
        @ColumnInfo(name = "name")
        val name: String = "",
        @ColumnInfo(name = "types")
        val types: List<String>? = emptyList(),
        @ColumnInfo(name = "photos")
        val photos: List<Photo>?,
        @ColumnInfo(name = "rating")
        val rating: Float?,
        @ColumnInfo(name = "reviews")
        val reviews: List<ReviewEntity>? = null
) {

    fun toModel(): Place =
            Place(placeId, reference, formatted_address, icon, name, types, photos, rating,
                    reviews?.let { toReviewModel(it) })

        private fun toReviewModel(jsonList: List<ReviewEntity>): List<Review> {
                val list : MutableList<Review> = mutableListOf()
                jsonList.map {
                        list.add(it.toReview())
                }

                return list
        }

}