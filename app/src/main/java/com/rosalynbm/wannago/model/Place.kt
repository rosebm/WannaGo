package com.rosalynbm.wannago.model

import com.rosalynbm.wannago.model.Result

data class Place(
        val html_attributions: List<Any>,
        val result: Result,
        val status: String
)