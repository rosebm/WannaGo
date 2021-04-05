package com.rosalynbm.wannago.model

import kotlinx.android.parcel.Parcelize

data class Response(
        val html_attributions: List<Any>,
        val place: Place,
        val status: String
)