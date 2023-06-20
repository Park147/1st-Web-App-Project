package com.example.teamproject.category

import MyAdapter
import MyAdapterListener
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamproject.DetailActivity
import com.example.teamproject.MyApplication
import com.example.teamproject.databinding.ActivityCategorytwoBinding
import com.example.teamproject.model.RstrModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryTwoActivity : AppCompatActivity(), MyAdapterListener {
    private lateinit var binding: ActivityCategorytwoBinding
    private lateinit var adapter: MyAdapter
    private var rstrList: List<RstrModel> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategorytwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "뒤로가기"

        val rstrService = (applicationContext as MyApplication).networkService

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

        binding.catebtn11.setOnClickListener {
            val filterText = "중구" // 변경 가능: 입력된 텍스트로 대체하면 됩니다.
            val filteredList = filterRstrList(filterText)
            if (filteredList.isNotEmpty()) {
                adapter.updateDatas(filteredList)
            } else {
                Toast.makeText(this, "해당 조건에 맞는 음식점이 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.catebtn22.setOnClickListener {
            val filterText = "종로구" // 변경 가능: 입력된 텍스트로 대체하면 됩니다.
            val filteredList = filterRstrList(filterText)
            if (filteredList.isNotEmpty()) {
                adapter.updateDatas(filteredList)
            } else {
                Toast.makeText(this, "해당 조건에 맞는 음식점이 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        binding.catebtn33.setOnClickListener {
            val filterText1 = "강서구"
            val filterText2 = "마포구"
            val filterText3 = "영등포구"
            val filterText4 = "서대문구"
            val filterText5 = "성동구" // 변경 가능: 입력된 텍스트로 대체하면 됩니다.

            val filteredList = rstrList.filter { rstr ->
                listOf(filterText1, filterText2, filterText3, filterText4, filterText5).any { filterText ->
                    rstr.rstr_addr.contains(filterText)
                }
            }

            if (filteredList.isNotEmpty()) {
                adapter.updateDatas(filteredList)
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
        startActivity(intent)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        Toast.makeText(this@CategoryTwoActivity, "뒤로가기", Toast.LENGTH_SHORT).show()
        return super.onSupportNavigateUp()
    }
}