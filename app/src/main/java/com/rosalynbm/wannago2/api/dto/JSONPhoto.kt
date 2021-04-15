package com.rosalynbm.wannago2.api.dto

import com.squareup.moshi.Json

class JSONPhoto(@Json(name = "height")
                val height: Int,
                @Json(name = "html_attributions")
                val html_attributions: List<String>,
                @Json(name = "photo_reference")
                val photo_reference: String,
                @Json(name = "width")
                val width: Int)