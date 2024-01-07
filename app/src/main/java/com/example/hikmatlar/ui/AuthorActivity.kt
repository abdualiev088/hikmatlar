package com.example.hikmatlar.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.hikmatlar.databinding.ActivityAuthorBinding

class AuthorActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAuthorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.allQuotes.setOnClickListener {
            Intent(this@AuthorActivity, MainActivity::class.java).apply {
                startActivity(this)
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
            finish()
    }

}