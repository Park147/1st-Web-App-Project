package com.example.teamproject.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teamproject.databinding.ItemRecyclerviewBinding
import com.example.teamproject.fragment.TwoWaitingFragment
import com.example.teamproject.model.ItemData

class MyReserveComViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class MyReserveComAdapter(val context: TwoWaitingFragment, val datas:List<ItemData>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyReserveComViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent,  false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyReserveComViewHolder).binding

        val waiting = datas?.get(position)
        binding.itemtitle.text = waiting?.w_title
        binding.itemcontent.text = waiting?.w_item
        binding.itemwaiting.text = waiting?.w_waiting
//        val urlImg = waiting?.w_image
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

