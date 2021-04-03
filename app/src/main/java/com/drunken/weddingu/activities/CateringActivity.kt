package com.drunken.weddingu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.weddingu.R
import com.drunken.weddingu.idget.LinearLayoutManager
import com.drunken.wadapters.CateringAdapters
import com.drunken.weddingu.databinding.ActivityCateringBinding
import com.drunken.weddingu.models.CateringModel
import java.util.*
import kotlin.collections.ArrayList

class CateringActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCateringBinding
    private lateinit var listModel : ArrayList<CateringModel>
    private lateinit var filteredList : ArrayList<CateringModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCateringBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listModel = setupCateringModel()
        filteredList = setupCateringModel()
        bindCateringToRv(filteredList)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun bindCateringToRv(catering: ArrayList<CateringModel>) {
        binding.rvDataCatering.layoutManager = LinearLayoutManager(this)
        binding.rvDataCatering.setHasFixedSize(true)

        val myAdapters = CateringAdapters(this, catering)
        binding.rvDataCatering.adapter = myAdapters

        var jmlhCatering = "%,d".format(myAdapters.itemCount)
        val jmlhCateringText = "$jmlhCatering Katering in Malang, Jawa Timur"
        binding.tvJmlhCatering.text = jmlhCateringText

        myAdapters.setOnClickListener(object : CateringAdapters.OnClickListener{
            override fun onClick(position: Int, model: CateringModel) {
                val intent = Intent(this@CateringActivity, DetailCatering::class.java)
                intent.putExtra("Katering Model", model)
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
                        jmlhCatering = "%,d".format(myAdapters.itemCount)
                        val jmlhCateringText = "$jmlhCatering Katering in Malang, Jawa Timur"
                        binding.tvJmlhCatering.text = jmlhCateringText
                    }
                } else {
                    filteredList.clear()
                    filteredList.addAll(listModel)
                    myAdapters.notifyDataSetChanged()
                    jmlhCatering = "%,d".format(myAdapters.itemCount)
                    val jmlhCateringText = "$jmlhCatering Katering in Malang, Jawa Timur"
                    binding.tvJmlhCatering.text = jmlhCateringText
                }
            }

        })
    }

    private fun setupCateringModel() : ArrayList<CateringModel> {
        val listImage1 = arrayListOf(R.drawable.catering1_example, R.drawable.catering1_example1, R.drawable.catering1_example2, R.drawable.catering1_example3)
        val listImage2 = arrayListOf(R.drawable.catering2_example, R.drawable.catering2_example1, R.drawable.catering2_example2, R.drawable.catering2_example3)
        val listImage3 = arrayListOf(R.drawable.catering3_example, R.drawable.catering3_example1, R.drawable.catering3_example2)
        val listImage4 = arrayListOf(R.drawable.catering4_example, R.drawable.catering4_example1, R.drawable.catering4_example2)
        val listImage5 = arrayListOf(R.drawable.catering5_example, R.drawable.catering5_example1, R.drawable.catering5_example2)

        val dataCatering1 = CateringModel(1, "Nutribox Catering", "Jl. Ikan Cakalang No.315B Malang, Jawa Timur", listImage1, 1200, 2000, 4.5, 15000000, "08727364")
        val dataCatering2 = CateringModel(2, "Berkah Catering", "Perumahan Agro Kencana AMU I No.6, Karangploso, Malang", listImage2, 1500, 2500, 4.7, 21125000, "08197463")
        val dataCatering3 = CateringModel(3, "Diva Catering", "Jalan Bahagia No. 17 Malang, Jawa Timur", listImage3, 1000, 1900, 4.2, 12575000, "08937462")
        val dataCatering4 = CateringModel(4, "Sonokembang Catering", "Jalan Sonokembang No. 6 Malang, Jawa Timur", listImage4, 900, 1800, 4.3, 11500000, "09836587")
        val dataCatering5 = CateringModel(5, "Anindhita Catering", "Jalan Anindhita No. 2 Malang, Jawa Timur", listImage5, 1300, 2000, 4.0, 14250000, "08937461")

        val CateringModel = arrayListOf(dataCatering1, dataCatering2, dataCatering3, dataCatering4, dataCatering5)

        return CateringModel
    }
}