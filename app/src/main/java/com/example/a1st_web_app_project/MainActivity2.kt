package com.example.a1st_web_app_project

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a1st_web_app_project.databinding.ActivityMain2Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding



    override fun onCreate(savedInstanceState: Bundle?) {
        val serviceKey = "DLqEcgtBqy4lMOUw1BMi8NL1H5XiTbNKGuQtPM3epTpiPJZa6jcwQo1DDRmsGstF"

        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "뒤로가기"



        val networkService = (applicationContext as MyApplication).networkService

        // 부산 도보여행 정보 서비스 : getList2, PageListModel2.
        // 부산맛집정보성비스 : getList, PageListModel
        //부산맛집 _순서1
        val userListCall = networkService.getList(serviceKey)

        Log.d("hjm", "url:" + userListCall.request().url().toString())

        //부산맛집 _ 순서2
        userListCall.enqueue(object : Callback<PageListModel> {
            //부산 맛집 _ 순서3
            override fun onResponse(call: Call<PageListModel>, response: Response<PageListModel>) {

                Log.d("hjm","실행 여부 확인. userListCall.enqueue")
                val userList = response.body()
                //부산맛집로그 _ 순서4
                Log.d("hjm","userList data 값 : ${userList?.body}")
                Log.d("hjm","userList data 갯수 : ${userList?.body}")

                //부산맛집 순서5
                binding.recyclerView.adapter= MyAdapter(this@MainActivity2,userList?.body)

                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(this@MainActivity2, LinearLayoutManager.VERTICAL)
                )

            }

            //부산맛집 _순서6
            override fun onFailure(call: retrofit2.Call<PageListModel>, t: Throwable) {
                Log.d("hjm","fail")
                call.cancel()

            }

        })


    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        Toast.makeText(this@MainActivity2, "뒤로가기", Toast.LENGTH_SHORT).show()
        return super.onSupportNavigateUp()
    }}
