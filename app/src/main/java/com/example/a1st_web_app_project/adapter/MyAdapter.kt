package com.example.a1st_web_app_project

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.a1st_web_app_project.databinding.ItemMainBinding
import com.example.a1st_web_app_project.model.RstrModel


class MyViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val context: Context, val datas: List<RstrModel>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding


        val rs = datas?.get(position)
        binding.id.text = rs?.rstr_nm
        binding.firstNameView.text = "주소: "+rs?.rstr_addr
        binding.contactView.text = "전화: "+rs?.rstr_tell
        binding.contactView.text = "업종: "+rs?.rstr_list
        binding.contactView.text = "소개: "+rs?.rstr_intro

        val imageUrl = rs?.rstr_img

        if(imageUrl != null) {
            Glide.with(context)
                .load(imageUrl)
                .into(binding.avatarView)
        }

    }}
