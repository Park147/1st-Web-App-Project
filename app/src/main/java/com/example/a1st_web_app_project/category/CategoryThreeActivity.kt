package com.example.a1st_web_app_project.category

import MyAdapter
import MyAdapterListener
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a1st_web_app_project.DetailActivity
import com.example.a1st_web_app_project.MyApplication
import com.example.a1st_web_app_project.databinding.ActivityCategorythreeBinding
import com.example.a1st_web_app_project.model.RstrModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryThreeActivity : AppCompatActivity(), MyAdapterListener {
    private lateinit var binding: ActivityCategorythreeBinding
    private lateinit var adapter: MyAdapter
    private var rstrList: List<RstrModel> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategorythreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "뒤로가기"

        val rstrService = (applicationContext as MyApplication).rstrService

        val getRstrList = rstrService.getRstrList()
        Log.d("jcy", "${getRstrList.request().url().toString()}")

        getRstrList.enqueue(object : Callback<List<RstrModel>> {
            override fun onResponse(
                call: Call<List<RstrModel>>,
                response: Response<List<RstrModel>>
            ) {
                if (response.isSuccessful) {
                    rstrList = response.body() ?: emptyList()
                    Log.d("jcy", "$rstrList")
                    adapter.updateDatas(rstrList)
                }
            }

            override fun onFailure(call: Call<List<RstrModel>>, t: Throwable) {
                Log.d("jcy", "실패 ${t.message}")
                call.cancel()
            }
        })

        adapter = MyAdapter(this, rstrList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        adapter.setListener(this)

        binding.catebtn111.setOnClickListener {
            val filteredList = filterRstrList()
            if (filteredList.isNotEmpty()) {
                adapter.updateDatas(filteredList)
            } else {
                Toast.makeText(this, "해당 조건에 맞는 음식점이 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.catebtn222.setOnClickListener {
            val filteredList = filterRstrList2()
            if (filteredList.isNotEmpty()) {
                adapter.updateDatas(filteredList)
            } else {
                Toast.makeText(this, "해당 조건에 맞는 음식점이 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.catebtn333.setOnClickListener {
            val filteredList = filterRstrList3()
            if (filteredList.isNotEmpty()) {
                adapter.updateDatas(filteredList)
            } else {
                Toast.makeText(this, "해당 조건에 맞는 음식점이 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun filterRstrList(): List<RstrModel> {
        val filteredList = mutableListOf<RstrModel>()

        for (rstr in rstrList) {
            if (rstr.rstr_list == "한식") {
                filteredList.add(rstr)
            }
        }

        return filteredList
    }

    private fun filterRstrList2(): List<RstrModel> {
        val filteredList = mutableListOf<RstrModel>()

        for (rstr in rstrList) {
            if (rstr.rstr_list == "경양식") {
                filteredList.add(rstr)
            }
        }

        return filteredList
    }

    private fun filterRstrList3(): List<RstrModel> {
        val filteredList = mutableListOf<RstrModel>()

        for (rstr in rstrList) {
            if (rstr.rstr_list == "중국식") {
                filteredList.add(rstr)
            }
        }

        return filteredList
    }

    override fun onItemClick(data: RstrModel) {
        val intent = Intent(this@CategoryThreeActivity, DetailActivity::class.java).apply {
            putExtra("rstr_nm", data.rstr_nm)
            putExtra("rstr_img", data.rstr_img)
            putExtra("rstr_addr", data.rstr_addr)
            putExtra("rstr_tell", data.rstr_tell)
            putExtra("rstr_intro", data.rstr_intro)
            putExtra("rstr_popularity", data.rstr_popularity)
        }
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        Toast.makeText(this@CategoryThreeActivity, "뒤로가기", Toast.LENGTH_SHORT).show()
        return super.onSupportNavigateUp()
    }
}