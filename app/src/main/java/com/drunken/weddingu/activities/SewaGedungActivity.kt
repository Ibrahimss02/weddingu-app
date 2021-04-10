package com.drunken.weddingu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.drunken.weddingu.R
import com.drunken.weddingu.adapters.SewaGedungAdapter
import com.drunken.weddingu.databinding.ActivitySewaGedungBinding
import com.drunken.weddingu.firebase.Firestore
import com.drunken.weddingu.models.GedungModel
import java.util.*
import kotlin.collections.ArrayList

class SewaGedungActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySewaGedungBinding
    private lateinit var listModel : ArrayList<GedungModel>
    private lateinit var filteredList : ArrayList<GedungModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySewaGedungBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listModel = setupGedungModel()
        filteredList = setupGedungModel()
        bindGedungToRv(filteredList)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.btnList.setOnClickListener {
            startActivity(Intent(this, KeranjangActivity::class.java))
            onPause()
        }
    }

    private fun bindGedungToRv(gedung: ArrayList<GedungModel>) {
        binding.rvDataGedung.layoutManager = LinearLayoutManager(this)
        binding.rvDataGedung.setHasFixedSize(true)

        val myAdapters = SewaGedungAdapter(this, gedung)
        binding.rvDataGedung.adapter = myAdapters

        var jmlhGedung = "%,d".format(myAdapters.itemCount)
        val jmlhGedungText = "$jmlhGedung Gedung in Malang, Jawa Timur"
        binding.tvJmlhGedung.text = jmlhGedungText

        myAdapters.setOnClickListener(object : SewaGedungAdapter.OnClickListener{
            override fun onClick(position: Int, model: GedungModel) {
                val intent = Intent(this@SewaGedungActivity, DetailGedung::class.java)
                intent.putExtra("Gedung Model", model)
                startActivity(intent)
            }
        })


        binding.etSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(s.toString().isNotEmpty()){
                    filteredList.clear()
                    val searchText = s.toString().toLowerCase()
                    listModel.forEach {
                        if (it.name.toLowerCase().contains(searchText)){
                            filteredList.add(it)
                        }
                        myAdapters.notifyDataSetChanged()
                        jmlhGedung = "%,d".format(myAdapters.itemCount)
                        val jmlhGedungText = "$jmlhGedung Gedung in Malang, Jawa Timur"
                        binding.tvJmlhGedung.text = jmlhGedungText
                    }
                } else {
                    filteredList.clear()
                    filteredList.addAll(listModel)
                    myAdapters.notifyDataSetChanged()
                    jmlhGedung = "%,d".format(myAdapters.itemCount)
                    val jmlhGedungText = "$jmlhGedung Gedung in Malang, Jawa Timur"
                    binding.tvJmlhGedung.text = jmlhGedungText
                }
            }

        })
    }

    private fun setupGedungModel() : ArrayList<GedungModel> {
        val listImage1 = arrayListOf(R.drawable.wedding_venue_example, R.drawable.wedding_venue_example1, R.drawable.wedding_venue_example2, R.drawable.wedding_venue_example3)
        val listImage2 = arrayListOf(R.drawable.wedding_venue2_example, R.drawable.wedding_venue2_example1, R.drawable.wedding_venue2_example2)
        val listImage3 = arrayListOf(R.drawable.wedding_venue3_example, R.drawable.wedding_venue3_example1, R.drawable.wedding_venue3_example2)
        val listImage4 = arrayListOf(R.drawable.wedding_venue4_example, R.drawable.wedding_venue4_example1, R.drawable.wedding_venue4_example2)
        val listImage5 = arrayListOf(R.drawable.wedding_venue5_example, R.drawable.wedding_venue5_example1, R.drawable.wedding_venue5_example2)
        val listImage6 = arrayListOf(R.drawable.wedding_venue6_example, R.drawable.wedding_venue6_example1, R.drawable.wedding_venue6_example2)
        val listImage7 = arrayListOf(R.drawable.wedding_venue7_example, R.drawable.wedding_venue7_example1, R.drawable.wedding_venue7_example2)
        val listImage8 = arrayListOf(R.drawable.wedding_venue8_example, R.drawable.wedding_venue8_example1, R.drawable.wedding_venue8_example2)

        val dataGedung1 = GedungModel(1, "Gedung Pahlawan", "Jalan Pahlawan No. 16 Malang, Jawa Timur", listImage1, 1200, 2000, 4.5, 15000000, "04232920")
        val dataGedung2 = GedungModel(2, "Gedung Sejahtera", "Jalan Sejahtera No. 7 Malang, Jawa Timur", listImage2, 1500, 2500, 4.7, 21125000, "04232002")
        val dataGedung3 = GedungModel(3, "Gedung Bahagia", "Jalan Bahagia No. 17 Malang, Jawa Timur", listImage3, 1000, 1900, 4.2, 12575000, "04232007")
        val dataGedung4 = GedungModel(4, "Gedung Damai", "Jalan Damai No. 6 Malang, Jawa Timur", listImage4, 900, 1800, 4.3, 11500000, "04231212")
        val dataGedung5 = GedungModel(5, "Gedung Bahtera", "Jalan Bahtera No. 2 Malang, Jawa Timur", listImage5, 1300, 2000, 4.0, 14250000, "04231209")
        val dataGedung6 = GedungModel(6, "Gedung Selamat", "Jalan Selamat No. 1 Malang, Jawa Timur", listImage6, 1700, 3000, 4.5, 26356000, "04231111")
        val dataGedung7 = GedungModel(7, "Gedung John", "Jalan Cena No. 9 Malang, Jawa Timur", listImage7, 1500, 2700, 4.3, 20025000, "0423568")
        val dataGedung8 = GedungModel(8, "Gedung London", "Jalan Inggris No. 77 Malang, Jawa Timur", listImage8, 2000, 3250, 4.5, 28956000, "0423568")


        val gedungModel = arrayListOf(dataGedung1, dataGedung2, dataGedung3, dataGedung4, dataGedung5, dataGedung6, dataGedung7, dataGedung8)

        Firestore().putGedungModel(this, gedungModel)


        return gedungModel
    }
}