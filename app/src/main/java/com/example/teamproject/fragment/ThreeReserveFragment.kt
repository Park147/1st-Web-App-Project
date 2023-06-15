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
import com.example.teamproject.MainActivity
import com.example.teamproject.MyApplication
import com.example.teamproject.databinding.FragmentThreeReserveBinding
import com.example.teamproject.databinding.ItemRecyclerviewBinding
import com.example.teamproject.model.ItemDataList
import com.example.teamproject.recycler.MyReserveAdapter
import com.example.teamproject.recycler.MyReserveCanAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThreeReserveFragment : Fragment() {
    lateinit var binding: FragmentThreeReserveBinding
    lateinit var adapter: MyReserveCanAdapter
    lateinit var bindingRe: ItemRecyclerviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThreeReserveBinding.inflate(inflater, container, false)
        bindingRe = ItemRecyclerviewBinding.inflate(inflater, container, false)

        val networkService = (context?.applicationContext as MyApplication).networkService
        val reserveListCall = networkService.getReserve()

        reserveListCall.enqueue(object : Callback<ItemDataList> {
            override fun onResponse(call: Call<ItemDataList>, response: Response<ItemDataList>) {
                var item = response.body()?.items

                adapter = MyReserveCanAdapter(this@ThreeReserveFragment, item)
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