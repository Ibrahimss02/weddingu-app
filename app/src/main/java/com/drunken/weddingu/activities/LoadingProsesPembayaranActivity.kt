package com.drunken.weddingu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.drunken.weddingu.R
import com.drunken.weddingu.firebase.Firestore
import java.util.*
import kotlin.concurrent.schedule

class LoadingProsesPembayaranActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_proses_pembayaran)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        Timer("Load to Final Screen", false).schedule(4000){
            Firestore().clearCart(this@LoadingProsesPembayaranActivity)
        }
    }

    fun finishActivity(result: String){
        if (result == "Sukses"){
            startActivity(Intent(this, TransaksiSuksesActivity::class.java))
            finish()
        } else {
            onBackPressed()
            showErrorSnackBar("Something wrong. Try Again")
        }
    }
}