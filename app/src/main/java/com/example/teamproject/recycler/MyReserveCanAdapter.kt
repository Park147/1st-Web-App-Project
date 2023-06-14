package com.example.teamproject.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teamproject.databinding.ItemRecyclerviewBinding
import com.example.teamproject.fragment.ThreeReserveFragment
import com.example.teamproject.model.ItemData

class MyReserveCanViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class MyReserveCanAdapter(val context: ThreeReserveFragment, val datas:List<ItemData>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyReserveCanViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent,  false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyReserveCanViewHolder).binding

        val reserve = datas?.get(position)
        binding.itemtitle.text = reserve?.r_title
        binding.itemcontent.text = reserve?.r_item
        binding.itemwaiting.text = reserve?.r_waiting
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
    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }
}

