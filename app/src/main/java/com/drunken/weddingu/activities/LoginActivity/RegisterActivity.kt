package com.drunken.weddingu.activities.LoginActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.drunken.weddingu.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val buttonRegister
        buttonRegister.setOnClickListener{
            onBackPressed()
        }

        override fun onBackPressed() {
            super.onBackPressed()
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide__to_right)
        }
    }
}