package com.example.teamproject.fragment

import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamproject.MyApplication
import com.example.teamproject.databinding.BookmarkMainBinding
import com.example.teamproject.databinding.FragmentReviewBinding
import com.example.teamproject.model.Bookmark
import com.example.teamproject.recycler.BookAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewFragment : Fragment() {
    lateinit var binding: FragmentReviewBinding
    lateinit var adapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentReviewBinding.inflate(inflater, container, false)

        val loginSharedPref = requireActivity().getSharedPreferences("login_prof", Context.MODE_PRIVATE)
        val userId = loginSharedPref.getString("m_id", null)

        val userService = (context?.applicationContext as MyApplication).userService
        val booklist = userService.bmList(userId.toString())



        booklist.enqueue(object : Callback<List<Bookmark>> {
            override fun onResponse(call: Call<List<Bookmark>>, response: Response<List<Bookmark>>) {
                var item = response.body()
            }

            override fun onFailure(call: Call<List<Bookmark>>, t: Throwable) {
                call.cancel()
            }

        })

        return binding.root
    }

}