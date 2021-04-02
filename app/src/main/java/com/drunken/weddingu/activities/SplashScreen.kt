package com.drunken.weddingu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.drunken.weddingu.R
import com.drunken.weddingu.databinding.ActivitySplashScreenBinding
import com.drunken.weddingu.firebase.Firestore
import com.google.firebase.auth.FirebaseAuth
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

        binding.wLogoLoading.startAnimation(topAnim)
        binding.titleLoading.startAnimation(bottomAnim)

        binding.mainScreen.animate().translationY((-1600F)).setDuration(1000).startDelay = 1500
        binding.titleLoading.animate().translationX(1400F).setDuration(1000).startDelay = 5500
        binding.wLogoLoading.animate().translationX(1400F).setDuration(1000).startDelay = 5500

        Timer("Starting", false).schedule(2350){
            val currentUserID = Firestore().getCurrentUserID()

            if (currentUserID.isNotEmpty()){
                startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            } else {
                startActivity(Intent(this@SplashScreen, GetStarted::class.java))
            }
            overridePendingTransition(0,0)
            finish()
        }

    }
}