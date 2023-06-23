package com.example.teamproject.category

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamproject.DetailActivity
import com.example.teamproject.MyApplication
import com.example.teamproject.databinding.ActivityCategoryoneBinding
import com.example.teamproject.model.RstrModel
import com.example.teamproject.recycler.MyAdapter
import com.example.teamproject.recycler.MyAdapterListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryOneActivity : AppCompatActivity(), MyAdapterListener {
    private lateinit var binding: ActivityCategoryoneBinding
    private lateinit var adapter: MyAdapter
    private var rstrList: List<RstrModel> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "뒤로가기"

        val rstrService = (applicationContext as MyApplication).userService

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

        binding.catebtn1.setOnClickListener {
            val filteredList = rstrList.filter { rstr ->
                rstr.rstr_popularity != null && rstr.rstr_popularity > 0.toString() // 인기순위 조건을 여기에 추가해주세요. 예시로는 인기순위가 0보다 큰 음식점을 필터링합니다.
            }

            if (filteredList.isNotEmpty()) {
                val sortedList = filteredList.sortedByDescending { rstr -> rstr.rstr_popularity } // 인기순위로 정렬
                adapter.updateDatas(sortedList)
            } else {
                Toast.makeText(this, "해당 조건에 맞는 음식점이 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun filterRstrList(vararg filterTexts: String): List<RstrModel> {
        val filteredList = mutableListOf<RstrModel>()

        for (rstr in rstrList) {
            val containsAllFilters = filterTexts.all { rstr.rstr_addr.contains(it) }
            if (containsAllFilters) {
                filteredList.add(rstr)
            }
        }

        return filteredList
    }

    override fun onItemClick(data: RstrModel) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("rstr_nm", data.rstr_nm)
        intent.putExtra("rstr_img", data.rstr_img)
        intent.putExtra("rstr_addr", data.rstr_addr)
        intent.putExtra("rstr_tell", data.rstr_tell)
        intent.putExtra("rstr_intro", data.rstr_intro)
        intent.putExtra("rstr_popularity", data.rstr_popularity)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        Toast.makeText(this@CategoryOneActivity, "뒤로가기", Toast.LENGTH_SHORT).show()
        return super.onSupportNavigateUp()
    }
}