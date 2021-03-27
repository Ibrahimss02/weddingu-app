package com.drunken.weddingu.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.drunken.weddingu.R
import com.drunken.weddingu.databinding.ActivityProfileBinding
import com.drunken.weddingu.databinding.ActivityRegisterBinding
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity(){

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNavMenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_nav_home -> {
                    startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
                    finish()
                }
            }
            true
        }
    }
}