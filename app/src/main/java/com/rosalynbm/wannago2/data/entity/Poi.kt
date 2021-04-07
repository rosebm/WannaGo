package com.rosalynbm.wannago2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "pois")
class Poi (
    @ColumnInfo(name = "location") var location: String?,
    @ColumnInfo(name = "description") var description: String?,
    @ColumnInfo(name = "latitude") var latitude: Double?,
    @ColumnInfo(name = "longitude") var longitude: Double?,
    @ColumnInfo(name = "place_id") var placeId: String?,
    @PrimaryKey
    @ColumnInfo(name = "entry_id") val id: String = UUID.randomUUID().toString()
)