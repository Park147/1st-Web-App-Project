package com.example.k0201_jcy_test

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teamproject.MyApplication
import com.example.teamproject.databinding.ItemCommentBinding
import com.example.teamproject.model.Comment
import com.example.teamproject.model.CommentU
import com.example.teamproject.review.CommentActivity
import com.example.teamproject.review.CommentUpdateActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentViewHolder(val binding: ItemCommentBinding): RecyclerView.ViewHolder(binding.root)

class CommentAdapter(val context: Context, val datas: List<Comment>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = CommentViewHolder(ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as CommentViewHolder).binding

        val loginSharedPref = context.getSharedPreferences("login_prof", Context.MODE_PRIVATE)
        val userId = loginSharedPref.getString("m_id", null)

        val userService = ( context.applicationContext as MyApplication).userService

        //관광기후
        val comment = datas?.get(position)
        binding.commentid.text = comment?.c_id
        binding.commentcontent.text = comment?.c_content
        binding.commentdate.text = comment?.c_modifydate

        var c_getnum = comment?.c_getnum?.toInt() ?:0
        var c_num = comment?.c_num?.toInt() ?:0

        if ( userId == comment?.c_id){
            binding.comUpdate.setVisibility(View.VISIBLE)
            binding.comDelete.setVisibility(View.VISIBLE)
        } else {
            binding.comUpdate.setVisibility(View.INVISIBLE)
            binding.comDelete.setVisibility(View.INVISIBLE)
        }


        binding.comDelete.setOnClickListener {
            val comD = userService.commentD(c_num)
            comD.enqueue(object: Callback<Unit>{
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful){
                        Log.d("comdeletetest","성공!")
                        val intent = Intent(context, CommentActivity::class.java)
                        intent.putExtra("r_num", c_getnum)
                        context.startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    call.cancel()
                }

            })

        }

        binding.comUpdate.setOnClickListener {
            val comG = userService.commentG(c_num)
            comG.enqueue(object: Callback<Comment>{
                override fun onResponse(call: Call<Comment>, response: Response<Comment>) {
                    if ( response.isSuccessful) {
                        val comGet = response.body()

                        val intent = Intent(context, CommentUpdateActivity::class.java)
                        intent.putExtra("c_num", comGet?.c_num)
                        intent.putExtra("c_id", comGet?.c_id)
                        intent.putExtra("c_content", comGet?.c_content)
                        intent.putExtra("c_getnum", comGet?.c_getnum)
                        context.startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<Comment>, t: Throwable) {
                    call.cancel()
                }

            })
        }



    }
}