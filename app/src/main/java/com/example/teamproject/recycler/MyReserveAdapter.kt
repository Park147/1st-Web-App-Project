package com.example.teamproject.recycler


import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.icu.lang.UCharacter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.teamproject.MyApplication
import com.example.teamproject.databinding.ItemRecyclerviewBinding
import com.example.teamproject.fragment.OneReserveFragment
import com.example.teamproject.model.ItemData

class MyReserveViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class MyReserveAdapter(val context: OneReserveFragment, val datas:List<ItemData>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyReserveViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent,  false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyReserveViewHolder).binding

        val reserve = datas?.get(position)

        binding.itemtitle.text = reserve?.r_title
        binding.itemcontent.text = reserve?.r_item
        binding.itemwaiting.text = reserve?.r_waiting
        binding.cancelbutton.isInvisible = true
        val urlImg = reserve?.r_image

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

