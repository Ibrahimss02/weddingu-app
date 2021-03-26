package com.drunken.weddingu.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.drunken.weddingu.databinding.ActivityDetailGedungBinding

class DetailGedung : AppCompatActivity() {

    private lateinit var binding : ActivityDetailGedungBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGedungBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}