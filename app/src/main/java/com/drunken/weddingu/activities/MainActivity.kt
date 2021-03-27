package com.drunken.weddingu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.drunken.weddingu.R
import com.drunken.weddingu.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView

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

        binding.bottomNavMenu.selectedItemId = R.id.bottom_nav_home

        binding.bottomNavMenu.setOnNavigationItemReselectedListener(object : BottomNavigationView.OnNavigationItemReselectedListener{
            override fun onNavigationItemReselected(item: MenuItem) {
                when(item.itemId){
                    R.id.bottom_nav_account -> {
                        // Implement Intent Here
                    }
                    R.id.bottom_nav_message -> {
                        // Implement Intent Here
                    }
                    R.id.bottom_nav_search -> {
                        // Implement Intent Here
                    }
                    R.id.bottom_nav_home -> {
                        // Implement Intent Here
                    }
                }
            }

        })
    }

}