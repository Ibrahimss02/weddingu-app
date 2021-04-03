package com.drunken.weddingu.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.drunken.weddingu.adapters.ViewPagerAdapter
import com.drunken.weddingu.databinding.ActivityDetailCateringBinding
import com.drunken.weddingu.models.CateringModel

class DetailCatering : AppCompatActivity() {

    private lateinit var binding : ActivityDetailCateringBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCateringBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var cateringDetailModel : CateringModel? = null

        if(intent.hasExtra("Catering Model")){
            cateringDetailModel = intent.getSerializableExtra("Catering Model") as CateringModel
        }

        if (cateringDetailModel != null){
            val viewPagerAdapter = ViewPagerAdapter(this, cateringDetailModel.image)
            binding.detailCateringViewPager.adapter = viewPagerAdapter
            binding.bannerIndicatorDetailCatering.setViewPager(binding.detailCateringViewPager)
            binding.namaCateringDetail.text = cateringDetailModel.name
            binding.alamatCateringDetail.text = cateringDetailModel.alamat
            binding.ratingCateringDetail.text = cateringDetailModel.rating.toString()
            val luasGedung = "%,d".format(cateringDetailModel.luas)
            binding.luasGedungDetail.text = "$luasGedung m"
            val kapasitasGedung = "%,d".format(cateringDetailModel.kapasitas)
            binding.kapasitasGedungDetail.text = kapasitasGedung
            val hargaCatering = "%,d".format(cateringDetailModel.harga)
            binding.tarifGedungDetail.text = "Rp $hargaCatering/paket"
        }

        binding.exitBtn.setOnClickListener {
            onBackPressed()
        }

        binding.contactBtn.setOnClickListener {
            val dial = "tel:${cateringDetailModel!!.kontak}"
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(dial)))
        }
    }
}