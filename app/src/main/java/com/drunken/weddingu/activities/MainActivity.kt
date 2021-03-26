package com.drunken.weddingu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.drunken.weddingu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivGedung.setOnClickListener {
            val intent = Intent(this, SewaGedungActivity::class.java)
            startActivity(intent)
            onPause()
        }
        binding.ivGedungCategories.setOnClickListener {
            val intent = Intent(this, SewaGedungActivity::class.java)
            startActivity(intent)
            onPause()
        }

    }

}