package com.example.teamproject.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamproject.MyApplication
import com.example.teamproject.MyDining
import com.example.teamproject.databinding.FragmentThreeWaitingBinding
import com.example.teamproject.model.ItemDataList
import com.example.teamproject.recycler.MyDeleteAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThreeWaitingFragment : Fragment(){
    lateinit var binding: FragmentThreeWaitingBinding
    lateinit var adapter: MyDeleteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThreeWaitingBinding.inflate(inflater, container, false)

        val networkService = (context?.applicationContext as MyApplication).networkService
        val reserveListCall = networkService.getWaitingAll()

        reserveListCall.enqueue(object : Callback<ItemDataList> {
            override fun onResponse(call: Call<ItemDataList>, response: Response<ItemDataList>) {
                var item = response.body()?.items

                adapter = MyDeleteAdapter(this@ThreeWaitingFragment, item, networkService)
                adapter.filter.filter("방문취소")

                binding.threeRecyclerView.adapter = adapter
                binding.threeRecyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ItemDataList>, t: Throwable) {
                call.cancel()
            }
        })

        return binding.root
    }

}