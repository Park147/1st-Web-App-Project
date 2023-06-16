package com.example.teamproject.recycler

import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teamproject.MyApplication
import com.example.teamproject.R
import com.example.teamproject.databinding.ItemMainBinding
import com.example.teamproject.login.LoginActivity
import com.example.teamproject.model.Bookmark
import com.example.teamproject.model.Rstr
import com.example.teamproject.model.Rstrbook
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val context: Context, val datas: List<Rstr>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding


        //받아온 데이터를 아이템에 넣기 위한 설정
        val rs = datas?.get(position)
        binding.id.text = rs?.rstr_nm
        binding.firstNameView.text = "주소: "+rs?.rstr_addr
        binding.secondNameView.text = "전화: "+rs?.rstr_tell
        binding.thirdNameView.text = "업종: "+rs?.rstr_list
        binding.fourthNameView.text = "소개: "+rs?.rstr_intro
        binding.bookmarkbtn.contentDescription = rs?.rstr_nm
        Log.d("markviewtest1", "성공! ${rs?.markview}")

        // 데이터 값을 비교해서 상황에 따라 출력하는 구간
        if (rs?.markview == "X") {
            Log.d("markviewtest2", "성공! ${rs?.markview}")
            binding.bookmarkbtn.setImageResource(R.drawable.bookmarkoff)
            binding.bookmarkbtn.setTag(R.string.image_resource_name, "bookmarkoff")
        } else if (rs?.markview == "O") {
            Log.d("markviewtest3", "실패! ${rs?.markview}")
            binding.bookmarkbtn.setImageResource(R.drawable.bookmarkon)
            binding.bookmarkbtn.setTag(R.string.image_resource_name, "bookmarkon")
        }

        binding.bookmarkbtn.setOnClickListener {
            val loginSharedPref = context.getSharedPreferences("login_prof", Context.MODE_PRIVATE)
            val userId = loginSharedPref.getString("m_id", null)
            if ( userId == null)
            {
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
            }
            else {
                val userService = ( context.applicationContext as MyApplication).userService
                val b_name = binding.bookmarkbtn.contentDescription as String
                val imgd = binding.bookmarkbtn.getTag(R.string.image_resource_name) as String?
                Log.d("imgbtn","이미지 이름: ${imgd}, url: ${rs?.rstr_img}")
                Toast.makeText(context, "식당 이름: ${b_name}, 이미지 이름: ${imgd}", Toast.LENGTH_SHORT).show()
                if ( imgd == "bookmarkon")
                {
                    val delbook = userService.bmdelete(userId, b_name)
                    delbook.enqueue(object: Callback<Unit>{
                        override fun onResponse(
                            call: Call<Unit>,
                            response: Response<Unit>
                        ) {
                            if (response.isSuccessful)
                            {
                                Log.d("bookmarkup", "성공!")
                            }
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            Log.d("bookmarkd", "실패 ${t.message}")
                            call.cancel()
                        }

                    })
                    var rstrbook = Rstrbook(
                        rstr_nm = b_name!!.toString(),
                        markview = "X".toString()
                    )
                    Log.d("bookmarkup", "${rstrbook}")
                    val updateBook = userService.upbookview(rstrbook)
                    updateBook.enqueue(object: Callback<Unit>{
                        override fun onResponse(
                            call: Call<Unit>,
                            response: Response<Unit>
                        ) {
                            if(response.isSuccessful) {
                                binding.bookmarkbtn.setImageResource(R.drawable.bookmarkoff)
                                binding.bookmarkbtn.setTag(
                                    R.string.image_resource_name,
                                    "bookmarkoff"
                                )
                                Log.d("bookmarkup", "성공!")
                            }
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            Log.d("bookmarkup", "실패 ${t.message}")
                            call.cancel()
                        }

                    })
                } else if ( imgd == "bookmarkoff")
                {
                    var bookmark = Bookmark(
                        b_id = userId.toString(),
                        b_name = b_name!!.toString(),
                        b_imgurl = rs?.rstr_img.toString()
                    )
                    Log.d("bookmarkup", "${bookmark}")
                    val addbook = userService.bmregister(bookmark)
                    addbook.enqueue(object: Callback<Unit>{
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            if (response.isSuccessful)
                            {
                                Log.d("bookmarkup", "성공!")
                            }
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            Log.d("bookmarka", "실패 ${t.message}")
                            call.cancel()
                        }

                    })
                    var rstrbook = Rstrbook(
                        rstr_nm = b_name!!.toString(),
                        markview = "O".toString()
                    )
                    Log.d("bookmarkup", "${rstrbook}")
                    val updateBook = userService.upbookview(rstrbook)
                    updateBook.enqueue(object: Callback<Unit>{
                        override fun onResponse(
                            call: Call<Unit>,
                            response: Response<Unit>
                        ) {
                            binding.bookmarkbtn.setImageResource(R.drawable.bookmarkon)
                            binding.bookmarkbtn.setTag(R.string.image_resource_name, "bookmarkon")
                            Log.d("bookmarkup", "성공!")
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            Log.d("bookmarkup", "실패 ${t.message}")
                            call.cancel()
                        }

                    })
                }

            }

        }
        
        // 이미지의 주소가 url일 경우 출력하는 구간
        val imageUrl = rs?.rstr_img

        if (imageUrl != null) {
            Glide.with(context)
                .load(imageUrl)
                .into(binding.avatarView)
        }
    }
}