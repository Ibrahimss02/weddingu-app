package com.drunken.weddingu.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.drunken.weddingu.adapters.ViewPagerAdapter
import com.drunken.weddingu.databinding.ActivityDetailGedungBinding
import com.drunken.weddingu.firebase.Firestore
import com.drunken.weddingu.models.GedungModel
import java.util.jar.Manifest

class DetailGedung : AppCompatActivity() {

    private lateinit var binding : ActivityDetailGedungBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGedungBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var gedungDetailModel : GedungModel? = null

        if(intent.hasExtra("Gedung Model")){
            gedungDetailModel = intent.getSerializableExtra("Gedung Model") as GedungModel
        }

        if (gedungDetailModel != null){
            val viewPagerAdapter = ViewPagerAdapter(this, gedungDetailModel.image)
            binding.detailGedungViewPager.adapter = viewPagerAdapter
            binding.bannerIndicatorDetailGedung.setViewPager(binding.detailGedungViewPager)
            binding.namaGedungDetail.text = gedungDetailModel.name
            binding.alamatGedungDetail.text = gedungDetailModel.alamat
            binding.ratingGedungDetail.text = gedungDetailModel.rating.toString()
            val luasGedung = "%,d".format(gedungDetailModel.luas)
            binding.luasGedungDetail.text = "$luasGedung m"
            val kapasitasGedung = "%,d".format(gedungDetailModel.kapasitas)
            binding.kapasitasGedungDetail.text = kapasitasGedung
            val hargaGedung = "%,d".format(gedungDetailModel.harga)
            binding.tarifGedungDetail.text = "Rp $hargaGedung/night"
        }

        binding.exitBtn.setOnClickListener {
            onBackPressed()
        }

        binding.contactBtn.setOnClickListener {
            val dial = "tel:${gedungDetailModel!!.kontak}"
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(dial)))
        }

        binding.listIconDetailGedung.setOnClickListener {
            startActivity(Intent(this, KeranjangActivity::class.java))
            onPause()
        }

        binding.addToCartBtn.setOnClickListener {
            Firestore().addToCartGedung(this, gedungDetailModel!!)
        }
    }
}