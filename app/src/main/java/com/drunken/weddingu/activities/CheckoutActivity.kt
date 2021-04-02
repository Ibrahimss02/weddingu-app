package com.drunken.weddingu.activities

import android.os.Bundle
import android.view.View
import com.drunken.weddingu.databinding.ActivityCheckoutBinding
import com.drunken.weddingu.firebase.Firestore
import com.drunken.weddingu.models.GedungModel
import com.drunken.weddingu.models.User
import com.google.firebase.firestore.FirebaseFirestore

class CheckoutActivity : BaseActivity() {

    private lateinit var binding : ActivityCheckoutBinding
    private var firestore = FirebaseFirestore.getInstance()
    private var totalHarga = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Firestore().getUserData(this)

        binding.btnCheckoutBack.setOnClickListener {
            onBackPressed()
        }

    }

    fun setUserData(user : User){
        setUserInfoOnUI(user)
    }

    fun setGedungModel(user : User){
        if(user.gedungModel != 0){
            firestore.collection("Gedung Model").document(user.gedungModel.toString()).get().addOnSuccessListener { document ->
                val gedungModel = document.toObject(GedungModel::class.java)!!
                setGedungModelToList(gedungModel)
            }
        }
    }

    fun setUserInfoOnUI(user : User){
        binding.tvCheckoutNama.text = "A/n ${user.username}"
        binding.tvCheckoutNomor.text = user.handphoneNumber
        binding.tvCheckoutTanggal.text = "Tanggal 25/01/2021"
    }

    fun setGedungModelToList(gedungModel: GedungModel){
        binding.checkoutGedungIv.setImageResource(gedungModel.image[0])
        binding.checkoutNamaGedungTv.text = gedungModel.name
        val hargaSewaGedung = "%,d".format(gedungModel.harga)
        binding.checkoutGedungTarif.text = "Rp $hargaSewaGedung"
        totalHarga += gedungModel.harga
        binding.cvCheckoutItemPertama.visibility = View.VISIBLE
        displayTotalHarga()
    }

    fun displayTotalHarga(){
        val totalHargaFormatted = "%,d".format(totalHarga)
        binding.tvCheckoutTotalHarga.text = "Rp $totalHargaFormatted"
    }
}