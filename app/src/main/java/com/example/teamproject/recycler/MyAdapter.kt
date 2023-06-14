package com.example.teamproject.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teamproject.databinding.ItemMainBinding
import com.example.teamproject.model.ItemModel2
import com.example.teamproject.model.ItemModel4


class MyViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val context: Context, val datas: List<ItemModel2>?, val datas2: List<ItemModel4>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding


        //관광기후
        val rs = datas?.get(position)
        val rsimg = datas2?.get(position)
        binding.firstNameView.text = "지역이름: "+rs?.RSTR_ID
        binding.secondNameView.text = "코스이름: "+rs?.RSTR_NM
        binding.thirdNameView.text = "관광지명: "+rs?.RSTR_TELNO
        binding.fourthNameView.text = "일 3시간 기온: "+rs?.BSNS_STATM_BZCND_NM

        val imageUrl = rsimg?.RSTR_IMG_URL

        if (imageUrl != null) {
            Glide.with(context)
                .load(imageUrl)
                .into(binding.avatarView)
        }
    }
}