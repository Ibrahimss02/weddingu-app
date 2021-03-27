package com.drunken.weddingu.activities.LoginActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.drunken.weddingu.R
import com.drunken.weddingu.activities.MainActivity
import com.drunken.weddingu.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonregister.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }

        binding.registerTv.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}