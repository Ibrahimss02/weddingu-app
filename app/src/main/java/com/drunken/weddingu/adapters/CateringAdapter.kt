package com.drunken.weddingu.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drunken.weddingu.databinding.CateringItemBinding
import com.drunken.weddingu.models.CateringModel

class CateringAdapter(private val context: Context, private val listCatering : ArrayList<CateringModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(CateringItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = listCatering[position]

        if(holder is MyViewHolder){
            holder.bind(model)
        }
    }

    override fun getItemCount(): Int {
        return listCatering.size
    }

    private class MyViewHolder(val binding : CateringItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(cateringModel: CateringModel){
            binding.rvNamaCatering.text = cateringModel.name
            binding.rvAlamatCatering.text = cateringModel.alamat
            binding.rvGambarCatering.setImageResource(cateringModel.image[0])
            binding.rvRatingCatering.text = cateringModel.rating.toString()
        }
    }
}