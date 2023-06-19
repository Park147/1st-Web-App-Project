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
import com.example.teamproject.databinding.FragmentOneAlarmBinding
import com.example.teamproject.model.BlankItemList
import com.example.teamproject.recycler.MyAlarmAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OneAlarmFragment : Fragment() {
    lateinit var binding: FragmentOneAlarmBinding
    lateinit var adapter: MyAlarmAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneAlarmBinding.inflate(inflater, container, false)

        val networkService = (context?.applicationContext as MyApplication).networkService
        val reserveListCall = networkService.getBlank()

        reserveListCall.enqueue(object : Callback<BlankItemList> {
            override fun onResponse(call: Call<BlankItemList>, response: Response<BlankItemList>) {
                var item = response.body()?.blankItems

                adapter = MyAlarmAdapter(TwoFragment(), item)
                adapter.filter.filter("빈자리 알림")

                binding.oneAlarmRecyclerView.adapter = adapter
                binding.oneAlarmRecyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<BlankItemList>, t: Throwable) {
                call.cancel()
            }
        })

        binding.deleteBtn.setOnClickListener {
            var title = binding.deleteTitle.text.toString()
            var confirm = "빈자리 알림"
            val networkService = (context?.applicationContext as MyApplication).networkService
            val reserveDeleteCall = networkService.deleteBlankList(title)

            reserveDeleteCall.enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    Toast.makeText(context,"success", Toast.LENGTH_SHORT).show()
                    val intent= Intent(context, MyDining::class.java)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Toast.makeText(context,"fail", Toast.LENGTH_SHORT).show()
                }

            })
        }

        return binding.root
    }

}