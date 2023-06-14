package com.example.teamproject.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teamproject.recycler.MyReserveAdapter
import com.example.teamproject.MainActivity
import com.example.teamproject.MyApplication
import com.example.teamproject.MyDining
import com.example.teamproject.databinding.FragmentOneReserveBinding
import com.example.teamproject.databinding.ItemRecyclerviewBinding
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
            val networkService = (context?.applicationContext as MyApplication).networkService
            val reserveListCall = networkService.getReserve()
            Log.d("lmj", "url: " + reserveListCall.request().url().toString())

            reserveListCall.enqueue(object : Callback<ItemDataList> {
                override fun onResponse(call: Call<ItemDataList>, response: Response<ItemDataList>) {
                    var item = response.body()?.items
                    Log.d("lmj","ListItem 값 ${item}")

                    val layoutManager = LinearLayoutManager(context)
                    layoutManager.orientation = LinearLayoutManager.HORIZONTAL
                    binding.oneRecyclerView.layoutManager = layoutManager

                    adapter = MyReserveAdapter(this@OneReserveFragment, item)
                    binding.oneRecyclerView.adapter = adapter
                    binding.oneRecyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<ItemDataList>, t: Throwable) {
                    Log.d("lmj","fail: " + t.message)
                    call.cancel()
                }

            })

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
//                call.cancle()
//            }
//        })

        return binding.root
    }

}