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
import com.example.teamproject.databinding.ItemBookBinding
import com.example.teamproject.fragment.BookmarkFragment
import com.example.teamproject.model.Bookmark

class BookViewHolder(val binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root)

class BookAdapter(val context: BookmarkFragment, val datas:List<Bookmark>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        BookViewHolder(
            ItemBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as BookViewHolder).binding


        val book = datas?.get(position)
        binding.bname.text = book?.b_name
        val urlImg = book?.b_imgurl

        if (urlImg != null) {
            Glide.with(context)
                .load(urlImg)
                .into(binding.bookview)
        }
    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }
}