package com.example.a1st_web_app_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a1st_web_app_project.databinding.ActivityMain2Binding
import com.example.a1st_web_app_project.model.RstrModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "뒤로가기"



        val RstrService = (applicationContext as MyApplication).rstrService


        var getRstrList = RstrService.getRstrList()
        Log.d("jcy", "${getRstrList.request().url().toString()}")

        getRstrList.enqueue(object: Callback<List<RstrModel>> {
            override fun onResponse(call: Call<List<RstrModel>>, response: Response<List<RstrModel>>) {
                if(response.isSuccessful) {
                    val rstrList = response.body()
                    Log.d("jcy", "${rstrList}")
                    binding.recyclerView.adapter = MyAdapter(this@MainActivity2, rstrList)

                    binding.recyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity2, LinearLayoutManager.VERTICAL))

                }
            }

            override fun onFailure(call: Call<List<RstrModel>>, t: Throwable) {
                Log.d("jcy", "실패 ${t.message}")
                call.cancel()
            }

//    }
//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        Toast.makeText(this@MainActivity2, "뒤로가기", Toast.LENGTH_SHORT).show()
//        return super.onSupportNavigateUp()
    })}}
