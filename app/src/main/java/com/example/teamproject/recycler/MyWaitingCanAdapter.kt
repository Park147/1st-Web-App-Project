package com.example.teamproject.recycler

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.teamproject.MyApplication
import com.example.teamproject.MyDining
import com.example.teamproject.databinding.ItemRecyclerviewBinding
import com.example.teamproject.fragment.ThreeWaitingFragment
import com.example.teamproject.model.ItemData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyReserveCanViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class MyReserveCanAdapter(val context: ThreeWaitingFragment, val datas:List<ItemData>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyReserveCanViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent,  false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyReserveCanViewHolder).binding
        val waiting = datas?.get(position)

        binding.cancelbutton.setOnClickListener {
            val networkService = (context.context?.applicationContext as MyApplication).networkService
            val requestCall: Call<Unit> = networkService.deleteWaitingList(waiting?.w_title)
            requestCall.enqueue(object : Callback<Unit> {

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    val intent= Intent(holder.itemView?.context,MyDining::class.java)
                    startActivity(holder.itemView.context,intent,null)
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                }

            })
        }
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

