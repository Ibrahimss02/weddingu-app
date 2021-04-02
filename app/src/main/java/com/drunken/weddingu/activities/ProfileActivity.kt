package com.drunken.weddingu.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.drunken.weddingu.R
import com.drunken.weddingu.databinding.ActivityProfileBinding
import com.drunken.weddingu.firebase.Firestore
import com.drunken.weddingu.models.User
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : BaseActivity(){

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavMenu.selectedItemId = R.id.bottom_nav_account
        Firestore().getUserData(this)


        binding.bottomNavMenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_nav_home -> {
                    startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
                    finish()
                    overridePendingTransition(0, 0)
                }
            }
            true
        }

        binding.signOutTv.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            Toast.makeText(this, "Sign out Success", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    fun updateUserDetails(user : User){
        Glide.with(this).load(user.imageProfile).centerCrop().placeholder(R.drawable.ic_account_circle).into(binding.profileImageAccount)
        binding.usernameView2.text = user.username
        binding.emailView2.text = user.email
        binding.addressView2.text = user.address
        binding.numberView2.text = user.handphoneNumber
    }
}