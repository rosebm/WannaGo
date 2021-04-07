package com.rosalynbm.wannago2.model

data class Response(
        val html_attributions: List<Any>,
        val place: Place,
        val status: String
)