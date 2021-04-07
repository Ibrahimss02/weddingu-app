package com.drunken.weddingu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.drunken.weddingu.R
import com.drunken.weddingu.databinding.ActivityTransferBankEndBinding
import com.drunken.weddingu.firebase.Firestore

class TransferBankEnd : AppCompatActivity() {

    private lateinit var binding : ActivityTransferBankEndBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferBankEndBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val totalHarga = intent.getIntExtra("Harga", 0)
        val gambarBank = intent.getIntExtra("Gambar Bank", 0)
        binding.tvBayarTotalHarga.text = "Rp ${"%,d".format(totalHarga)}"
        binding.tvMetode.text = intent.getStringExtra("Metode")

        if(gambarBank != 0){
            binding.ivMetodeBayar.setImageResource(gambarBank)
        }

        binding.barPetunjukTfMbanking.setOnClickListener{
            if (binding.tipsTfMbanking.visibility == View.GONE){
                binding.panahBarPetunjukTfMbanking.setImageResource(R.drawable.ic_arrow_up)
                binding.tipsTfMbanking.visibility = View.VISIBLE
            } else {
                binding.panahBarPetunjukTfMbanking.setImageResource(R.drawable.ic_arrow_down)
                binding.tipsTfMbanking.visibility = View.GONE
            }
        }

        binding.btnOk.setOnClickListener {
            startActivity(Intent(this, LoadingProsesPembayaranActivity::class.java))
            finish()
        }

        binding.btnInfoBayarBack.setOnClickListener { onBackPressed() }
    }
}