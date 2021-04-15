package com.rosalynbm.wannago2.model

import com.rosalynbm.wannago2.data.entity.PlaceEntity

data class Response(
        val html_attributions: List<Any>,
        val placeEntity: PlaceEntity,
        val status: String
)