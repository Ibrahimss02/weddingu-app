package com.drunken.weddingu.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.drunken.weddingu.adapters.ViewPagerAdapter
import com.drunken.weddingu.databinding.ActivityDetailGedungBinding
import com.drunken.weddingu.firebase.Firestore
import com.drunken.weddingu.models.GedungModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest

class DetailGedung : BaseActivity() {

    private lateinit var binding : ActivityDetailGedungBinding
    private var date : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGedungBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var gedungDetailModel : GedungModel? = null

        binding.calendarDetailGedung.minDate = Calendar.getInstance().timeInMillis

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
            binding.tarifGedungDetail.text = "Rp $hargaGedung/malam"
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
            if(date == null){
                Toast.makeText(this, "Tanggal belum dipilih", Toast.LENGTH_SHORT).show()
            } else {
                Firestore().addToCartGedung(this, gedungDetailModel!!)
            }
        }

        binding.calendarDetailGedung.setOnDateChangeListener { _, year, month, dayOfMonth ->
            date = "$dayOfMonth/${month + 1}/$year"
            Firestore().setTanggal(date!!)
        }
    }
}