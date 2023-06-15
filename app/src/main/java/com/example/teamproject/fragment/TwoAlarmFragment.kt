package com.example.teamproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamproject.MyApplication
import com.example.teamproject.databinding.FragmentTwoAlarmBinding
import com.example.teamproject.model.ItemDataList
import com.example.teamproject.recycler.MyAlarmAdapter
import com.example.teamproject.recycler.MyOpenAlarmAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TwoAlarmFragment : Fragment() {
   lateinit var binding: FragmentTwoAlarmBinding
    lateinit var adapter: MyOpenAlarmAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoAlarmBinding.inflate(inflater, container, false)

        val networkService = (context?.applicationContext as MyApplication).networkService
        val reserveListCall = networkService.getReserve()


        reserveListCall.enqueue(object : Callback<ItemDataList> {
            override fun onResponse(call: Call<ItemDataList>, response: Response<ItemDataList>) {
                var item = response.body()?.items

                adapter = MyOpenAlarmAdapter(this@TwoAlarmFragment, item)
                binding.TwoAlarmRecyclerView.adapter = adapter
                binding.TwoAlarmRecyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ItemDataList>, t: Throwable) {
                call.cancel()
            }

        })

        return binding.root
    }

}