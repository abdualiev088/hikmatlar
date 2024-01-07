package com.example.hikmatlar.Backend.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.habittracker.recyclerViewAdapter.MVVM.EntityQuote
import com.example.hikmatlar.custom.converter.HashtagListConverter

@Database(entities = [EntityQuote::class], version = 1, exportSchema = false)
@TypeConverters(HashtagListConverter::class)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun getNotesDao(): QuoteDao

    companion object {
        // Singleton prevents multiple
        // instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: QuoteDatabase? = null

        fun getDatabase(context: Context): QuoteDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuoteDatabase::class.java,
                    "quote_db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}