package com.example.teamproject.recycler


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teamproject.databinding.ItemBookBinding
import com.example.teamproject.databinding.ItemFollowingBinding
import com.example.teamproject.fragment.BookmarkFragment
import com.example.teamproject.fragment.FollowingFragment
import com.example.teamproject.model.Bookmark
import com.example.teamproject.model.Follow
import com.example.teamproject.retrofit.UserService

class FollowingViewHolder(val binding: ItemFollowingBinding): RecyclerView.ViewHolder(binding.root)

class FollowingAdapter(val context: FollowingFragment, val datas:List<Follow>?, val userService: UserService): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        FollowingViewHolder(
            ItemFollowingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as FollowingViewHolder).binding


        val follow = datas?.get(position)

        binding.fname.text = follow?.fr_id

    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }
}