package com.drunken.weddingu.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.drunken.weddingu.databinding.ActivityCheckoutBinding
import com.drunken.weddingu.firebase.Firestore
import com.drunken.weddingu.models.GedungModel
import com.drunken.weddingu.models.User
import com.google.firebase.firestore.FirebaseFirestore

class CheckoutActivity : BaseActivity() {

    companion object{
        private const val GET_METODE_PEMBAYARAN_TRANSFER_BANK_CODE = 1
    }
    private lateinit var binding : ActivityCheckoutBinding
    private var firestore = FirebaseFirestore.getInstance()
    private var totalHarga = 0
    private var intentToBayar : Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Firestore().getUserData(this)

        binding.btnCheckoutBack.setOnClickListener {
            onBackPressed()
        }
        binding.barPilihMetodePembayaran.setOnClickListener {
            val intent = Intent(this, MetodePembayaranActivity::class.java)
            startActivityForResult(intent, GET_METODE_PEMBAYARAN_TRANSFER_BANK_CODE)
        }

        binding.btnBayar.setOnClickListener {
            if (intentToBayar == null){
                Toast.makeText(this, "Metode Pembayaran belum ditentukan", Toast.LENGTH_SHORT).show()
            } else {
                toBayarActivity()
            }
        }
    }

    fun toBayarActivity(){
        startActivity(intentToBayar)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Metode TF BANK
        if (requestCode == GET_METODE_PEMBAYARAN_TRANSFER_BANK_CODE){
            if (resultCode == RESULT_OK){
                val hasil = data!!.getStringExtra("Hasil")
                binding.namaPembayaran.visibility = View.VISIBLE
                binding.namaMetodePembayaranTv.text = hasil
                intentToBayar = Intent(this, TransferBankEnd::class.java)
                intentToBayar!!.putExtra("Harga", totalHarga)
                intentToBayar!!.putExtra("Metode", data.getStringExtra("Metode"))
                intentToBayar!!.putExtra("Gambar Bank", data.getIntExtra("Gambar Bank", 0))
            }
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
        binding.tvCheckoutTanggal.text = "Tanggal ${user.tanggal}"
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