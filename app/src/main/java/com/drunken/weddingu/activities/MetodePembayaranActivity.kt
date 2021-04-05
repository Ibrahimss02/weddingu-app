package com.drunken.weddingu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.drunken.weddingu.R
import com.drunken.weddingu.adapters.ExpandableListAdapter
import com.drunken.weddingu.databinding.ActivityMetodePembayaranBinding

class MetodePembayaranActivity : BaseActivity() {

    private lateinit var binding : ActivityMetodePembayaranBinding
    private var titleList : List<String>? = null
    private var adapter : ExpandableListAdapter? = null

    private val data : HashMap<String, List<String>> get() {
        val listData = HashMap<String, List<String>>()

        val transferBank = ArrayList<String>()
        transferBank.add("Bank BCA")
        transferBank.add("Bank Mandiri")
        transferBank.add("Bank BRI")
        transferBank.add("Bank BNI")

        val dompetDigital = ArrayList<String>()
        dompetDigital.add("DANA")
        dompetDigital.add("OVO")
        dompetDigital.add("Gopay")
        dompetDigital.add("Link Aja")

        listData["Transfer Bank"] = transferBank
        listData["Dompet Digital"] = dompetDigital

        return listData
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMetodePembayaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listData = data
        titleList = ArrayList(listData.keys)

        adapter = ExpandableListAdapter(this, titleList as ArrayList<String>, listData)
        binding.exapandableListViewMetodePembayaran.setAdapter(adapter)

        binding.exapandableListViewMetodePembayaran.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            // Bank Mandiri Clicked
            if(childPosition == 1){
                Toast.makeText(this, "Bank Mandiri", Toast.LENGTH_SHORT).show()
            }
            false
        }
    }
}