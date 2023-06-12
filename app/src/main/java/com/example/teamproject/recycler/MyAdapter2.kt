package com.example.teamproject.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teamproject.databinding.ItemMainBinding
import com.example.teamproject.model.ItemModel4


class MyViewHolder2(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter2(val context: Context, val datas: List<ItemModel4>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder2(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder2).binding


        //관광기후
        val rsimg = datas?.get(position)

        val imageUrl = rsimg?.RSTR_IMG_URL

        if (imageUrl != null) {
            Glide.with(context)
                .load(imageUrl)
                .into(binding.avatarView)
        }

    }
}