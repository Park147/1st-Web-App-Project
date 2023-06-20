package com.example.teamproject.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamproject.DetailActivity
import com.example.teamproject.MainActivity
import com.example.teamproject.MyApplication
import com.example.teamproject.OnItemClickListener
import com.example.teamproject.databinding.FragmentOneWaitingBinding
import com.example.teamproject.model.ItemDataList
import com.example.teamproject.recycler.MyWaitingAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OneWaitingFragment : Fragment(), OnItemClickListener {
    lateinit var binding: FragmentOneWaitingBinding
    lateinit var adapter : MyWaitingAdapter

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
        val reserveListCall = networkService.getWaitingAll()

        reserveListCall.enqueue(object : Callback<ItemDataList> {
            override fun onResponse(call: Call<ItemDataList>, response: Response<ItemDataList>) {
                var item = response.body()?.items
                adapter = MyWaitingAdapter(this@OneWaitingFragment, item)
                adapter.filter.filter("방문예약")
                adapter.setOnItemClickListener(this@OneWaitingFragment)

                binding.oneRecyclerView.adapter = adapter
                binding.oneRecyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ItemDataList>, t: Throwable) {
                call.cancel()
            }


        })

        return binding.root
    }

    override fun onItemClick(title: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("title", title)
        startActivity(intent)
    }

}