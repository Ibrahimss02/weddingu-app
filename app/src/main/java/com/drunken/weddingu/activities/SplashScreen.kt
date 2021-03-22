package com.drunken.weddingu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.drunken.weddingu.R
import com.drunken.weddingu.databinding.ActivitySplashScreenBinding
import java.util.*
import kotlin.concurrent.schedule

class SplashScreen : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding
    private lateinit var topAnim : Animation
    private lateinit var bottomAnim : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        binding.iLogoLoading.startAnimation(topAnim)
        binding.wLogoLoading.startAnimation(topAnim)
        binding.titleLoading.startAnimation(bottomAnim)

        Timer("Starting", false).schedule(2000){
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
        }
    }
}