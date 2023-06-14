package com.example.a1st_web_app_project

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a1st_web_app_project.databinding.ActivityWaitingBinding
import com.example.a1st_web_app_project.model.RstrModel
import com.example.a1st_web_app_project.retrofit.RstrService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WaitingActivity : AppCompatActivity() {
    lateinit var binding: ActivityWaitingBinding
    private lateinit var editTextSearch: EditText
    private lateinit var buttonSearch: Button

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

        val RstrService = (applicationContext as MyApplication).rstrService

        val getRstrList = RstrService.getRstrList()
        Log.d("hjm", "${getRstrList.request().url().toString()}")

        getRstrList.enqueue(object : Callback<List<RstrModel>> {
            override fun onResponse(
                call: Call<List<RstrModel>>,
                response: Response<List<RstrModel>>
            ) {
                if (response.isSuccessful) {
                    val rstrList = response.body()
                    Log.d("hjm", "${rstrList}")
                    binding.recyclerView.adapter =
                        MyAdapter(this@WaitingActivity, rstrList)

                    binding.recyclerView.addItemDecoration(
                        DividerItemDecoration(
                            this@WaitingActivity,
                            LinearLayoutManager.VERTICAL
                        )
                    )
                }
            }

            override fun onFailure(call: Call<List<RstrModel>>, t: Throwable) {
                Log.d("hjm", "실패 ${t.message}")
                call.cancel()
            }
        })

        buttonSearch.setOnClickListener {
            val keyword = editTextSearch.text.toString().trim()

            if (keyword.isEmpty()) {
                Toast.makeText(this@WaitingActivity, "식당을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                // 검색 로직 실행
                // TODO: Implement search logic
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        Toast.makeText(this@WaitingActivity, "뒤로가기", Toast.LENGTH_SHORT).show()
        return super.onSupportNavigateUp()
    }
}

