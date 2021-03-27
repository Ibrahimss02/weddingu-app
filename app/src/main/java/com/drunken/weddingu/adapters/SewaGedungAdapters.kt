package com.drunken.weddingu.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drunken.weddingu.databinding.SewaGedungItemBinding
import com.drunken.weddingu.models.GedungModel

open class SewaGedungAdapters (private val context : Context, private var list : ArrayList<GedungModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(SewaGedungItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]

        if(holder is MyViewHolder){
            holder.bind(model)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private class MyViewHolder(val binding : SewaGedungItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(gedungModel : GedungModel){

            binding.rvGambarGedung.setImageResource(gedungModel.image[0])
            binding.rvNamaGedung.text = gedungModel.name
            binding.rvAlamatGedung.text = gedungModel.alamat
            binding.rvRatingGedung.text = gedungModel.rating.toString()
            val hargaGedung = "%,d".format(gedungModel.harga)
            binding.rvTarifGedung.text = "Rp $hargaGedung/night"
//            binding.rvJarakGedung.text = ??

        }
    }

}