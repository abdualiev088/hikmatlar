package com.example.habittracker.recyclerViewAdapter.MVVM

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hikmatlar.Backend.Room.QuoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteViewModel(application: Application): AndroidViewModel(application) {

    val repository : QuoteRepository

    val getAllQuotes : LiveData<List<EntityQuote>>

    init {
        val dao = QuoteDatabase.getDatabase(application).getNotesDao()
        repository = QuoteRepository(dao)
        getAllQuotes = repository.allQuotes
    }

    fun insertQuote(quote: EntityQuote) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(quote)
    }

}