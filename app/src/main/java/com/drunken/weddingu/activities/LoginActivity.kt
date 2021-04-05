package com.drunken.weddingu.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.drunken.weddingu.R
import com.drunken.weddingu.databinding.ActivityLoginBinding
import com.drunken.weddingu.firebase.Firestore
import com.drunken.weddingu.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : BaseActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.buttonregister.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }

        binding.registerTv.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }

        binding.buttonLogin.setOnClickListener {
            signIn()
        }
    }

    fun loginSuccess(user : User){
        hideProgressDialog()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun signIn(){
        val email = binding.email.text.toString().trim { it <= ' '}
        val password = binding.password.text.toString().trim { it <= ' ' }


        if (validateForm(email, password)) {
            showProgressDialog("Please wait")
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Firestore().getUserData(this)
                } else {
                    showErrorSnackBar("Authentication Error")
                }
                hideProgressDialog()
            }
        }
    }

    private fun validateForm(email : String, password : String) : Boolean{
        return when{
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter an email address")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter a password")
                false
            }
            else -> true
        }
    }
}