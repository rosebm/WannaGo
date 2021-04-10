package com.rosalynbm.wannago2.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rosalynbm.wannago2.data.entity.ReviewEntity
import com.rosalynbm.wannago2.model.Photo
import java.lang.reflect.Type
import java.util.*

class Converter {

    var gson: Gson = Gson()

    @TypeConverter
    fun stringToPhotoList(data: String?): List<Photo?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<Photo?>?>() {}.getType()
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun photoListToString(someObjects: List<Photo?>?): String? {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringToReviewList(data: String?): List<ReviewEntity?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<ReviewEntity?>?>() {}.getType()
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun reviewListToString(someObjects: List<ReviewEntity?>?): String? {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringToStringList(data: String?): List<String?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<String?>?>() {}.getType()
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun stringListToString(someObjects: List<String?>?): String? {
        return gson.toJson(someObjects)
    }
}