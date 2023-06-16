package com.example.teamproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teamproject.databinding.ItemPersonRecyclerviewBinding
import com.example.teamproject.recycler.PersonCount

class MyReserveViewHolder(val binding: ItemPersonRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class MyReserveAdapter(val datas: PersonCount?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyReserveViewHolder(ItemPersonRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent,  false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyReserveViewHolder).binding

        for(i in 1 .. 100) {
            binding.personCount.text = "$i 명"
        }

    }
    override fun getItemCount(): Int{

        return 0
    }
}

