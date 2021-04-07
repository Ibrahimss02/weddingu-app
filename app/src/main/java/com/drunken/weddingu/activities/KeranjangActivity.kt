package com.drunken.weddingu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import com.drunken.weddingu.databinding.ActivityKeranjangBinding
import com.drunken.weddingu.firebase.Firestore
import com.drunken.weddingu.models.GedungModel
import com.drunken.weddingu.models.User
import com.google.firebase.firestore.FirebaseFirestore

class KeranjangActivity : BaseActivity() {

    private lateinit var binding : ActivityKeranjangBinding
    private var firestore = FirebaseFirestore.getInstance()
    private lateinit var gedungModel: GedungModel
    private var totalItem = 0
    private var totalHarga = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKeranjangBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Firestore().getUserData(this)

        binding.llCartPertama.setOnClickListener {
            val intent = Intent(this, DetailGedung::class.java)
            intent.putExtra("Gedung Model", gedungModel)
            startActivity(intent)
            onPause()
        }

        binding.keranjangBackBtn.setOnClickListener { onBackPressed() }

        binding.btnTrashItemPertama.setOnClickListener {
            Firestore().clearCart(this)
        }

        binding.btnCheckout.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
            onPause()
        }
    }

    fun setGedungToList(user: User){
        firestore.collection("Gedung Model").document(user.gedungModel.toString()).get().addOnSuccessListener { document ->
            if(user.gedungModel != 0) {
                val gedungModel = document.toObject(GedungModel::class.java)
                this.gedungModel = gedungModel!!
                binding.keranjangNamaGedungTv.text = gedungModel.name
                val tarifGedung = "%,d".format(gedungModel.harga)
                binding.kerangjangGedungTarif.text = "Rp $tarifGedung"
                binding.keranjangGedungIv.setImageResource(gedungModel.image[0])
                binding.cvCartItemPertama.visibility = View.VISIBLE
                binding.listKosongTv.visibility = View.GONE
                totalItem += 1
                totalHarga += gedungModel.harga
                displayTotalItem()
                displayTotalHarga()
            }
        }
    }

    fun displayTotalItem(){
        if(totalItem <= 1){
            binding.tvTotalItemKeranjang.text = "Total $totalItem item"
        } else {
            binding.tvTotalItemKeranjang.text = "Total $totalItem items"
        }
    }

    fun displayTotalHarga(){
        val hargaTotalFormatted = "%,d".format(totalHarga)
        binding.tvTotalHargaKeranjang.text = "Rp $hargaTotalFormatted"
    }

    fun refreshActivity(){
        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

}