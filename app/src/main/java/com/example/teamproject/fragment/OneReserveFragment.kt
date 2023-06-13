package com.example.teamproject.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teamproject.recycler.MyReserveAdapter
import com.example.teamproject.MainActivity
import com.example.teamproject.MyApplication
import com.example.teamproject.R
import com.example.teamproject.databinding.FragmentOneReserveBinding
import com.example.teamproject.model.ItemData
import com.example.teamproject.model.ItemDataList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OneReserveFragment : Fragment() {
    lateinit var binding: FragmentOneReserveBinding
    lateinit var adapter: MyReserveAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneReserveBinding.inflate(inflater, container, false)

        binding.MainMenu.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

//        val networkService = (context?.applicationContext as MyApplication).networkService
//        val reserveList = networkService.getReserve()
//        reserveList.enqueue(object: Callback<ItemDataList>{
//            override fun onResponse(call: Call<ItemDataList>, response: Response<ItemDataList>) {
//
//                val item = response.body()?.items //responsebody에 있는 값을 가져옴
//                Log.d("lmj", "$item")
//
//                var memberCheck: List<String>?
//                all

//                adapter = MyReserveAdapter(this@OneReserveFragment, item)
//                binding.recyclerView.adapter = adapter
//                binding.recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
//                adapter.notifyDataSetChanged()
//
//                personal
//                var itemDataList = arrayListOf<ItemData>()
//                for(i in 0 until (itemList?.items?.size ?:0)){
//                    memberCheck = itemList?.items?.get(i)?.name?.split(",") ?: null
//
//                    if(memberCheck?.size?.minus(1) == 1){
//                        itemList?.items?.get(i)?.let { itemDataList?.add(it) }
//                    }
//                    adapter = MyReserveAdapter(this@OneReserveFragment, itemDataList)
//                }
//
//                people
//                var itemDataList = arrayListOf<ItemData>()
//                for(i in 0 until (itemList?.items?.size ?:0)){
//                    memberCheck = itemList?.items?.get(i)?.name?.split(",") ?: null
//                    if(memberCheck?.size?.minus(1)!! > 1){
//                        itemList?.items?.get(i)?.let { itemDataList?.add(it) }
//                    }
//                    adapter = MyReserveAdapter(this@OneReserveFragment, itemDataList)
//                }
//
//            }
//
//            }
//
//            override fun onFailure(call: Call<ItemDataList>, t: Throwable) {
//            }
//        })

        return binding.root
    }

}