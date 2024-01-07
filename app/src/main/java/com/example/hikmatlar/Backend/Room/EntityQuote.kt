package com.example.habittracker.recyclerViewAdapter.MVVM

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.hikmatlar.Backend.Api.ApiService.Hashtag
import com.example.hikmatlar.Backend.Api.ApiService.Quote
import com.example.hikmatlar.Backend.CommonDataClass
import java.time.LocalDateTime

@Entity(tableName = "quotes")
data class EntityQuote (
    @PrimaryKey(autoGenerate = true)
    override val idT: Long = 0,
    @ColumnInfo(name = "author")
    override val author: String,
    @ColumnInfo(name = "created_at")
    override val created_at: String,
    @ColumnInfo(name = "hashtags")
    override val hashtags: List<Hashtag>,
    @ColumnInfo(name = "id")
    override val id: Int,
    @ColumnInfo(name = "text")
    override val text: String,
    @ColumnInfo(name = "translation")
    override val translation: String,
) : CommonDataClass