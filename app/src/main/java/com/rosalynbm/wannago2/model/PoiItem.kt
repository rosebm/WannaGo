package com.rosalynbm.wannago2.model

import java.io.Serializable

/**
 * data class acts as a data mapper between the DB and the UI
 */
data class PoiItem (
    var location: String?,
    var description: String?,
    var latitude: Double?,
    var longitude: Double?,
    var placeId: String?,
    val id: String = java.util.UUID.randomUUID().toString()
) : Serializable