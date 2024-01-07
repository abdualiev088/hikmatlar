package com.example.hikmatlar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.viewpager2.widget.ViewPager2
import com.example.hikmatlar.R
import com.example.hikmatlar.ui.Slider.SliderViewPagerAdapter
import com.example.hikmatlar.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    lateinit var binding : ActivityIntroBinding

    private var titleList = mutableListOf<String>()
    private var authorList = mutableListOf<String>()

//    For Auto Scrolling
    private var currentPage = 0
    private var handler = Handler(Looper.getMainLooper())
    private lateinit var runnable : Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        postToList()

        val viewPager_2 = binding.viewPager
        viewPager_2.adapter = SliderViewPagerAdapter(titleList, authorList)
        viewPager_2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicator = binding.indicator
        indicator.setViewPager(viewPager_2)

        runnable = object : Runnable {
            override fun run() {
                if (currentPage == viewPager_2.adapter?.itemCount?.minus(1)) {
                    currentPage = 0 // Reset to first item if last item is reached
                } else {
                    currentPage++
                }
                viewPager_2.setCurrentItem(currentPage, true)
                handler.postDelayed(this, 4000)
            }
        }

        binding.btnStart.setOnClickListener {
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }
        val animation = AnimationUtils.loadAnimation(this, R.anim.btn_background_anim)
        binding.btnStartBack.startAnimation(animation)
    }

    override fun onStart() {
        super.onStart()
        handler.postDelayed(runnable, 2000)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }

    private fun addToList(title: String, author: String){
        titleList.add(title)
        authorList.add(author)
    }
    private fun postToList(){
        addToList("Tarbiya biz uchun yo hayot – yo mamot, yo najot – yo halokat, yo saodat – yo falokat masalasidir", "Abdulla Avloniy")
        addToList("Quyosh nurlarini berkitib bo’lmaganidek, haqiqatning chirog’ini ham so’ndirib bo’lmaydi.", "Abulqosim Zamaxshariy")
        addToList("Har bir inson har kuni qiladigan ishini xuddi birinchi marta qilayotgandek qilishi kerak. Shundagina ishda rivojlanish boʻladi.", "Shavkat Mirziyoyev")
    }
}
