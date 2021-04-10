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
            when(groupPosition){
                // Transfer Bank Method Group
                0 -> {
                    when(childPosition){
                        0 -> {
                            intent.putExtra("Hasil", "Transfer Bank BCA")
                            intent.putExtra("Metode", "Bank BCA")
                            intent.putExtra("Gambar Bank", R.drawable.ic_bank_bca)
                            setResult(RESULT_OK, intent)
                            finish()
                        }
                        1 -> {
                            intent.putExtra("Hasil", "Transfer Bank Mandiri")
                            intent.putExtra("Metode", "Bank Mandiri")
                            intent.putExtra("Gambar Bank", R.drawable.bank_mandiri)
                            setResult(RESULT_OK, intent)
                            finish()
                        }
                        2 -> {
                            intent.putExtra("Hasil", "Transfer Bank BRI")
                            intent.putExtra("Metode", "Bank BRI")
                            intent.putExtra("Gambar Bank", R.drawable.bank_bri)
                            setResult(RESULT_OK, intent)
                            finish()
                        }
                        3 -> {
                            intent.putExtra("Hasil", "Transfer Bank BNI")
                            intent.putExtra("Metode", "Bank BNI")
                            intent.putExtra("Gambar Bank", R.drawable.bank_bni)
                            setResult(RESULT_OK, intent)
                            finish()
                        }
                    }
                }
            }

            false
        }

        binding.btnMetodeBayarBack.setOnClickListener { onBackPressed() }
    }
}