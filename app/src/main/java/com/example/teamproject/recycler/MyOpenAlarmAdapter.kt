package com.example.teamproject.recycler

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.icu.lang.UCharacter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.teamproject.databinding.ItemRecyclerviewBinding
import com.example.teamproject.fragment.OneAlarmFragment
import com.example.teamproject.fragment.TwoAlarmFragment
import com.example.teamproject.model.BlankItem
import com.example.teamproject.model.BlankItemList
import com.example.teamproject.model.ItemData

class MyOpenAlarmViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class MyOpenAlarmAdapter(val context: TwoAlarmFragment, val datas:List<BlankItem>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyOpenAlarmViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent,  false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyOpenAlarmViewHolder).binding

        val waiting = datas?.get(position)
        binding.itemtitle.text = waiting?.b_title
        binding.itemcontent.text = waiting?.b_date
        binding.itemwaiting.text = waiting?.b_time
        val urlImg = waiting?.b_image

        Glide.with(context)
            .asBitmap()
            .load(urlImg)
            .into(object : CustomTarget<Bitmap>(200, 200) {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    binding.itemimage.setImageBitmap(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    UCharacter.GraphemeClusterBreak.T
                }
            })
    }
    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }
}