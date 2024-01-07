package com.example.hikmatlar.Backend.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.habittracker.recyclerViewAdapter.MVVM.EntityQuote
import com.example.hikmatlar.Backend.Api.ApiService.Quote
import com.example.hikmatlar.Backend.Api.ApiService.Quotes

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(quote: EntityQuote)

    @Query("SELECT * FROM quotes")
    fun getAllQuotes():LiveData<List<EntityQuote>>
}