package com.example.teamproject.fragment

import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamproject.recycler.MyReserveAdapter
import com.example.teamproject.MainActivity
import com.example.teamproject.MyApplication
import com.example.teamproject.databinding.FragmentOneWaitingBinding
import com.example.teamproject.model.ItemDataList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OneWaitingFragment : Fragment() {
    lateinit var binding: FragmentOneWaitingBinding
    lateinit var adapter: MyReserveAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOneWaitingBinding.inflate(inflater, container, false)

        binding.MainMenu.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
            val networkService = (context?.applicationContext as MyApplication).networkService
            val reserveListCall = networkService.getWaiting()


            reserveListCall.enqueue(object : Callback<ItemDataList> {
                override fun onResponse(call: Call<ItemDataList>, response: Response<ItemDataList>) {
                    var item = response.body()?.items

                    adapter = MyReserveAdapter(this@OneWaitingFragment, item)
                    binding.oneRecyclerView.adapter = adapter
                    binding.oneRecyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<ItemDataList>, t: Throwable) {
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