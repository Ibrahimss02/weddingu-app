package com.drunken.weddingu.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.drunken.weddingu.R
import com.drunken.weddingu.databinding.ImageItemBinding

class ViewPagerAdapter(private val context : Context, private val images : ArrayList<Int>) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(images : Int){
            binding.ivForBanner.setImageResource(images)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        return ViewPagerViewHolder(ImageItemBinding.inflate(LayoutInflater.from(context), parent , false))
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val images = images[position]

        if(holder is ViewPagerViewHolder){
            holder.bind(images)
        }

    }

    override fun getItemCount(): Int {
        return images.size
    }

}