package com.rosalynbm.wannago.ui.placelist

import java.io.Serializable

/**
 * data class acts as a data mapper between the DB and the UI
 */
data class PlaceItem (
    var location: String?,
    var description: String?,
    var latitude: Double?,
    var longitude: Double?,
    val id: String = java.util.UUID.randomUUID().toString()
    ) : Serializable