package com.example.teamproject

import MyAdapter
import MyAdapterListener
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamproject.databinding.ActivityWaitingBinding
import com.example.teamproject.model.RstrModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WaitingActivity : AppCompatActivity(), MyAdapterListener {
    private lateinit var binding: ActivityWaitingBinding
    private lateinit var editTextSearch: EditText
    private lateinit var buttonSearch: Button
    private lateinit var adapter: MyAdapter
    private var rstrList: List<RstrModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWaitingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "뒤로가기"

        editTextSearch = findViewById(R.id.editTextSearch)
        buttonSearch = findViewById(R.id.buttonSearch)

        val rstrService = (applicationContext as MyApplication).networkService

        val getRstrList = rstrService.getRstrList()
        Log.d("hjm", "${getRstrList.request().url().toString()}")

        getRstrList.enqueue(object : Callback<List<RstrModel>> {
            override fun onResponse(
                call: Call<List<RstrModel>>,
                response: Response<List<RstrModel>>
            ) {
                if (response.isSuccessful) {
                    rstrList = response.body()
                    Log.d("hjm", "${rstrList}")
                    rstrList?.let { adapter.updateDatas(it) }
                }
            }

            override fun onFailure(call: Call<List<RstrModel>>, t: Throwable) {
                Log.d("hjm", "실패 ${t.message}")
                call.cancel()
            }
        })

        adapter = MyAdapter(this, rstrList)
        adapter.setListener(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        buttonSearch.setOnClickListener {
            val keyword = editTextSearch.text.toString().trim()

            if (keyword.isEmpty()) {
                Toast.makeText(this@WaitingActivity, "식당을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                // 검색 로직 실행
                val filteredRstrList = rstrList?.filter { rstrModel ->
                    rstrModel.rstr_nm.contains(keyword, ignoreCase = true)
                }

                if (filteredRstrList.isNullOrEmpty()) {
                    Toast.makeText(this@WaitingActivity, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    adapter.updateDatas(filteredRstrList)
                }
            }
        }
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
        Toast.makeText(this@WaitingActivity, "뒤로가기", Toast.LENGTH_SHORT).show()
        return super.onSupportNavigateUp()
    }
}