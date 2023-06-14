package com.example.teamproject.recycler

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teamproject.R
import com.example.teamproject.databinding.ItemMainBinding
import com.example.teamproject.model.Rstr


class MyViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val context: Context, val datas: List<Rstr>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding


        //관광기후
        val rs = datas?.get(position)
        binding.id.text = rs?.rstr_nm
        binding.firstNameView.text = "주소: "+rs?.rstr_addr
        binding.secondNameView.text = "전화: "+rs?.rstr_tell
        binding.thirdNameView.text = "업종: "+rs?.rstr_list
        binding.fourthNameView.text = "소개: "+rs?.rstr_intro
        binding.bookmarkbtn.contentDescription = rs?.rstr_nm
        Log.d("markviewtest1", "성공! ${rs?.markview}")

        if (rs?.markview == "X") {
            Log.d("markviewtest2", "성공! ${rs?.markview}")
            binding.bookmarkbtn.setImageResource(R.drawable.bookmarkoff)
        } else if (rs?.markview == "O") {
            Log.d("markviewtest3", "실패! ${rs?.markview}")
            binding.bookmarkbtn.setImageResource(R.drawable.bookmarkon)
        }

        val imageUrl = rs?.rstr_img

        if (imageUrl != null) {
            Glide.with(context)
                .load(imageUrl)
                .into(binding.avatarView)
        }
    }
}