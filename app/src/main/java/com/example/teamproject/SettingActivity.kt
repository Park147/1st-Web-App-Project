package com.example.teamproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.teamproject.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "설정"

    }

    override fun onSupportNavigateUp(): Boolean {
        Toast.makeText(this@SettingActivity, "업버튼 클릭시 동작", Toast.LENGTH_SHORT).show()
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    
}