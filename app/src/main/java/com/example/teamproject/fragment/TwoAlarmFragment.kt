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
import com.example.teamproject.model.BlankItemList
import com.example.teamproject.recycler.MyAlarmAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TwoAlarmFragment : Fragment() {
   lateinit var binding: FragmentTwoAlarmBinding
    lateinit var adapter: MyAlarmAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoAlarmBinding.inflate(inflater, container, false)

        val networkService = (context?.applicationContext as MyApplication).networkService
        val reserveListCall = networkService.getBlank()

        reserveListCall.enqueue(object : Callback<BlankItemList> {
            override fun onResponse(call: Call<BlankItemList>, response: Response<BlankItemList>) {
                var item = response.body()?.blankItems

                adapter = MyAlarmAdapter(this@TwoAlarmFragment, item, networkService)
                adapter.filter.filter("예약 오픈 알림")
                binding.twoAlarmRecyclerView.adapter = adapter
                binding.twoAlarmRecyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<BlankItemList>, t: Throwable) {
                call.cancel()
            }

        })

        return binding.root
    }

}