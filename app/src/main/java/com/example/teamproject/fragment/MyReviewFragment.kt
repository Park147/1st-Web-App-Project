package com.example.teamproject.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamproject.MyApplication
import com.example.teamproject.databinding.FragmentMyreviewBinding
import com.example.teamproject.databinding.FragmentReviewBinding
import com.example.teamproject.model.Review
import com.example.teamproject.recycler.MyReviewAdapter
import com.example.teamproject.recycler.ReviewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyReviewFragment : Fragment() {
    lateinit var binding: FragmentMyreviewBinding
    lateinit var adapter: MyReviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMyreviewBinding.inflate(inflater, container, false)

        val loginSharedPref = requireActivity().getSharedPreferences("login_prof", Context.MODE_PRIVATE)
        val userId = loginSharedPref.getString("m_id", null)

        val userService = (context?.applicationContext as MyApplication).userService
        val reviewMyList = userService.getmyList(userId.toString())

        reviewMyList.enqueue(object : Callback<List<Review>> {
            override fun onResponse(call: Call<List<Review>>, response: Response<List<Review>>) {
                if (response.isSuccessful){
                    var item = response.body()
                    Log.d("reviewlist","$item")
                    adapter = MyReviewAdapter(this@MyReviewFragment, item, userId.toString(), userService)

                    binding.twoRecyclerView.adapter = adapter
                    binding.twoRecyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                    adapter.notifyDataSetChanged()
                }


            }

            override fun onFailure(call: Call<List<Review>>, t: Throwable) {
                call.cancel()
            }

        })

        return binding.root
    }

}