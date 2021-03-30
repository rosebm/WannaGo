package com.rosalynbm.wannago.model

data class Result(
        val formatted_address: String,
        val icon: String,
        val name: String,
        val photos: List<Photo>,
        val rating: Double,
        val reviews: List<Review>
)