package com.example.teamproject.recycler


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        Log.d("lmj","reserve?.r_title===========================${reserve?.r_title}")
        binding.itemtitle.text = reserve?.r_title
        Log.d("lmj","reserve?.r_item===========================${reserve?.r_item}")
        binding.itemcontent.text = reserve?.r_item
        Log.d("lmj","reserve?.r_waiting===========================${reserve?.r_waiting}")
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
        Log.d("lmj","dataSize = ${datas?.size ?: 0}")
        return datas?.size ?: 0
    }
}

