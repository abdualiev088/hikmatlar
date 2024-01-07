package com.example.hikmatlar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hikmatlar.R
import com.example.hikmatlar.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val quoteFragment = QuoteFragment()
        val savedQuotes = SavedFragment()

        supportActionBar?.hide()

//        this is an example for revert

        navView = binding.navView
        navView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.mainPage -> {
                    supportFragmentManager.apply {
                        beginTransaction().replace(R.id.contentView, quoteFragment)
                            .commit()
                    }
                    return@setOnItemSelectedListener true
                }

                R.id.saved -> {
                    supportFragmentManager.apply {
                        beginTransaction().replace(R.id.contentView, savedQuotes)
                            .commit()
                    }
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }
    }

}