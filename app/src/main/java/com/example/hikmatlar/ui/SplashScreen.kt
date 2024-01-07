package com.example.hikmatlar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.example.hikmatlar.R
import com.example.hikmatlar.databinding.SplashScreenBinding

class SplashScreen : AppCompatActivity() {
    lateinit var binding : SplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this, IntroActivity::class.java).apply{
                startActivity(this)
                finish()
            }
        }, 2000)

        val animation = AnimationUtils.loadAnimation(this, R.anim.splash_icon_anim)
        binding.iconSplash.startAnimation(animation)

    }
}