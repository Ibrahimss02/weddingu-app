package com.drunken.weddingu.activities

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.drunken.weddingu.R
import com.drunken.weddingu.databinding.ActivityRegisterBinding
import com.drunken.weddingu.firebase.Firestore
import com.drunken.weddingu.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : BaseActivity() {

    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonregister2.setOnClickListener{
            onBackPressed()
        }

        binding.buttonRegister.setOnClickListener{
            registerUser()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide__to_right)
    }

    fun userRegisteredSuccess(){
        Toast.makeText(this, "You have successfully registered", Toast.LENGTH_SHORT).show()
        hideProgressDialog()
        FirebaseAuth.getInstance().signOut()
        finish()
    }

    private fun registerUser(){
        val email : String = binding.enterEmail.text.toString().trim{ it <= ' '}
        val username : String = binding.username.text.toString().trim { it <= ' ' }
        val password : String = binding.password2.text.toString().trim{ it <= ' '}
        val handphoneNumber : String = binding.numberPhone.text.toString()
        val address : String = binding.address.text.toString()

        if (validateForm(email, username = username, password = password)){
            showProgressDialog("Please Wait")
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful){
                    val firebaseUser : FirebaseUser = it.result!!.user!!
                    val registeredEmail = firebaseUser.email!!
                    val user = User(firebaseUser.uid, username, email, handphoneNumber = handphoneNumber, address = address)
                    Firestore().registerUser(this, user)
                } else {
                    Toast.makeText(this, it.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validateForm(email : String, password : String, username : String) : Boolean{
        return when{
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter an email address")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter a password")
                false
            }
            TextUtils.isEmpty(username) -> {
                showErrorSnackBar("Please enter a username")
                false
            }
            else -> true
        }
    }
}