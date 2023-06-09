package com.example.teamproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.teamproject.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    lateinit var binding: ActivitySettingBinding

    var modprofileset: Button? = null
    var infomode: Button? = null
    var alamset: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "설정"

        modprofileset = binding.modprofileset
        infomode = binding.infomode
        alamset = binding. alamset

        binding.modprofileset.setOnClickListener {
            Toast.makeText(this@SettingActivity, "미구현", Toast.LENGTH_SHORT).show()
        }

        binding.infomode.setOnClickListener {
            Toast.makeText(this@SettingActivity, "미구현", Toast.LENGTH_SHORT).show()
        }

        binding.alamset.setOnClickListener {
            Toast.makeText(this@SettingActivity, "미구현", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        Toast.makeText(this@SettingActivity, "업버튼 클릭시 동작", Toast.LENGTH_SHORT).show()
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    
}