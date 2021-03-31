package com.drunken.weddingu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.drunken.weddingu.R
import com.drunken.weddingu.adapters.ViewPagerAdapter
import com.drunken.weddingu.databinding.ActivityMainBinding
import com.drunken.weddingu.firebase.Firestore
import com.drunken.weddingu.models.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Firestore().signInUser(this)

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

        binding.bottomNavMenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_nav_account -> {
                    startActivity(Intent(this@MainActivity, ProfileActivity::class.java))
                    finish()
                }
            }
            true
        }

        binding.ivProfileImageHomepage.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }

        val listImage1 = arrayListOf(R.drawable.wedding_venue_example, R.drawable.wedding_venue_example1, R.drawable.wedding_venue_example2)
        val adapter = ViewPagerAdapter(this, listImage1)
        binding.viewPagerHomepageBanner.adapter = adapter
        binding.bannerIndicatorHomepage.setViewPager(binding.viewPagerHomepageBanner)

    }

    fun updateImage(user : User){
        Glide.with(this).load(user.imageProfile).centerCrop().placeholder(R.drawable.profile_image_sample).into(binding.ivProfileImageHomepage)
    }

}