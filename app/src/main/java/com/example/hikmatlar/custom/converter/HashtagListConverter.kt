package com.example.hikmatlar.custom.converter

import androidx.room.TypeConverter
import com.example.hikmatlar.Backend.Api.ApiService.Hashtag
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HashtagListConverter {

    @TypeConverter
    fun fromString(value: String): List<Hashtag> {
        val listType = object : TypeToken<List<Hashtag>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(value: List<Hashtag>): String {
        return Gson().toJson(value)
    }
}