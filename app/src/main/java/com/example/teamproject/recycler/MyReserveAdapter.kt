package com.example.teamproject.recycler

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.icu.lang.UCharacter
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.teamproject.databinding.FragmentOneReserveBinding
import com.example.teamproject.databinding.ItemRecyclerviewBinding
import com.example.teamproject.fragment.OneReserveFragment
import com.example.teamproject.model.ItemData

class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class MyReserveAdapter(val context: OneReserveFragment, val items:List<ItemData>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        return items?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent,  false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding

        val reserve = items?.get(position)
        Log.d("lmj","reserve?.r_title===========================${reserve?.r_title}")
        binding.itemTitle.text = reserve?.r_title
        Log.d("lmj","reserve?.r_item===========================${reserve?.r_item}")
        binding.itemContent.text = reserve?.r_item
        Log.d("lmj","reserve?.r_waiting===========================${reserve?.r_waiting}")
        binding.itemWaiting.text = reserve?.r_waiting
//        val urlImg = reserve?.r_image
//
//        Glide.with(context)
//            .asBitmap()
//            .load(urlImg)
//            .into(object : CustomTarget<Bitmap>(200, 200) {
//                override fun onResourceReady(
//                    resource: Bitmap,
//                    transition: Transition<in Bitmap>?
//                ) {
//                    binding.itemImage.setImageBitmap(resource)
//                }
//
//                override fun onLoadCleared(placeholder: Drawable?) {
//                    UCharacter.GraphemeClusterBreak.T
//                }
//            })
    }
}

