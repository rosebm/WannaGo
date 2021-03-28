package com.rosalynbm.wannago.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "places")
class PlaceDTO (
    @ColumnInfo(name = "location") var location: String?,
    @ColumnInfo(name = "description") var description: String?,
    @ColumnInfo(name = "latitude") var latitude: Double?,
    @ColumnInfo(name = "longitude") var longitude: Double?,
    @PrimaryKey
    @ColumnInfo(name = "entry_id") val id: String = UUID.randomUUID().toString()
)