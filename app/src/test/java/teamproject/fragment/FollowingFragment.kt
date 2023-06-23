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
import com.example.teamproject.databinding.FragmentFollowingBinding
import com.example.teamproject.databinding.FragmentReviewBinding
import com.example.teamproject.model.Follow
import com.example.teamproject.model.Review
import com.example.teamproject.recycler.FollowingAdapter
import com.example.teamproject.recycler.ReviewAdapter
import com.example.teamproject.retrofit.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingFragment : Fragment() {
    lateinit var binding: FragmentFollowingBinding
    lateinit var adapter: FollowingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFollowingBinding.inflate(inflater, container, false)

        val loginSharedPref = requireActivity().getSharedPreferences("login_prof", Context.MODE_PRIVATE)
        val userId = loginSharedPref.getString("m_id", null)

        val userService = (context?.applicationContext as MyApplication).userService
        val followL = userService.followList(userId.toString())

        followL.enqueue(object : Callback<List<Follow>> {
            override fun onResponse(call: Call<List<Follow>>, response: Response<List<Follow>>) {
                if (response.isSuccessful){
                    var item = response.body()
                    Log.d("reviewlist","$item")
                    adapter = FollowingAdapter(this@FollowingFragment, item, userService)

                    binding.twoRecyclerView.adapter = adapter
                    binding.twoRecyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                    adapter.notifyDataSetChanged()
                }


            }

            override fun onFailure(call: Call<List<Follow>>, t: Throwable) {
                call.cancel()
            }

        })

        return binding.root
    }

}