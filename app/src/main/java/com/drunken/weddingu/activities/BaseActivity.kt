package com.drunken.weddingu.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.drunken.weddingu.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import kotlin.concurrent.schedule

open class BaseActivity : AppCompatActivity() {

    private var doubleBackToExit = false
    private lateinit var progressDialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }


    fun showProgressDialog(message : String){
        progressDialog = Dialog(this)
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.findViewById<TextView>(R.id.tv_progress_text).text = message
        progressDialog.show()
    }

    fun hideProgressDialog(){
        progressDialog.dismiss()
    }

    fun doubleBackToExit(){
        if (doubleBackToExit){
            onBackPressed()
            return
        }
        this.doubleBackToExit = true
        Toast.makeText(this, "Press Back Again to EXit", Toast.LENGTH_SHORT).show()

        Timer("Exit", false).schedule(2000){
            doubleBackToExit = false
        }
    }

    fun showErrorSnackBar(messange : String){
        val snackbar = Snackbar.make(findViewById(android.R.id.content), messange, Snackbar.LENGTH_LONG)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(ContextCompat.getColor(this, R.color.snackbar_error_color))
        snackbar.show()
    }
}