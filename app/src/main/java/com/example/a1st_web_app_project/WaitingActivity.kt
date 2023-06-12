package com.example.a1st_web_app_project

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a1st_web_app_project.databinding.ActivityWaitingBinding


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

            buttonSearch.setOnClickListener {
                val keyword = editTextSearch.text.toString().trim()

                if (keyword.isEmpty()) {
                    Toast.makeText(this@WaitingActivity, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
                } else {
                    // 검색 로직 실행
                }
            }
        }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        Toast.makeText(this@WaitingActivity, "뒤로가기", Toast.LENGTH_SHORT).show()
        return super.onSupportNavigateUp()
    }

}

