package com.rosalynbm.wannago2.data.entity

import androidx.room.Entity
import com.rosalynbm.wannago2.model.Review

@Entity(tableName = "review")
class ReviewEntity (
    private val author_name: String,
    private val author_url: String,
    private val language: String,
    private val profile_photo_url: String,
    val rating: Int,
    private val relative_time_description: String,
    val text: String,
    private val time: Int
) {

    fun toReview(): Review {
        return Review(author_name, author_url, language, profile_photo_url,
            rating, relative_time_description, text, time)
    }
}