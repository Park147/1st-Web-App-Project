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

import com.example.teamproject.MainActivity
import com.example.teamproject.MyApplication
import com.example.teamproject.recycler.MyAdapter
import com.example.teamproject.databinding.FragmentOneReserveBinding
import com.example.teamproject.model.ItemData
import retrofit2.Callback
import retrofit2.Response

class OneReserveFragment : Fragment() {
    lateinit var binding: FragmentOneReserveBinding
    var datas: MutableList<String>? = null
    lateinit var adapter: MyAdapter
    lateinit var userid: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneReserveBinding.inflate(inflater, container, false)

        binding.MainMenu.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }


        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val networkService = (requireActivity().applicationContext as MyApplication).networkService
        val usrListCall = networkService.reserve(userid)
        Log.d("lsy", "url:" + usrListCall.request().url().toString())

        usrListCall.enqueue(object : Callback<ItemData> {
            override fun onResponse(
                call: retrofit2.Call<ItemData>,
                response: Response<ItemData>
            ) {
                val usrList = response.body()
                binding.recyclerView.adapter =
                    MyAdapter(requireContext(), usrList)

                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL

            }

            override fun onFailure(call: retrofit2.Call<ItemData>, t: Throwable) {
                Log.d("lsy", "fail")
                call.cancel()
            }
        })
    }

}