package com.rosalynbm.wannago2.api.dto

import com.squareup.moshi.Json

class JSONResponse(@Json(name = "html_attributions")
                val html_attributions: List<Any>,
                   @Json(name = "result")
                val result: JSONPlace,
                   @Json(name = "status")
                val status: String)