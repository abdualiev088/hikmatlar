package com.example.habittracker.recyclerViewAdapter.MVVM

import androidx.lifecycle.LiveData
import com.example.hikmatlar.Backend.Api.ApiService.Quotes
import com.example.hikmatlar.Backend.Room.QuoteDao

class QuoteRepository(private var quotesDao: QuoteDao) {

    val allQuotes : LiveData<List<EntityQuote>> = quotesDao.getAllQuotes()

    suspend fun insert(quote: EntityQuote){
        quotesDao.insert(quote)
    }
}