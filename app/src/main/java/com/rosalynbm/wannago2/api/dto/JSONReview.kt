package com.rosalynbm.wannago2.api.dto

import com.squareup.moshi.Json

class JSONReview(@Json(name = "author_name")
                 val author_name: String,
                 @Json(name = "author_url")
                 val author_url: String,
                 @Json(name = "language")
                 val language: String,
                 @Json(name = "profile_photo_url")
                 val profile_photo_url: String,
                 @Json(name = "rating")
                 val rating: Int,
                 @Json(name = "relative_time_description")
                 val relative_time_description: String,
                 @Json(name = "text")
                 val text: String,
                 @Json(name = "time")
                 val time: Int)