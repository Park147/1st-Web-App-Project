package com.example.teamproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.teamproject.databinding.ActivitySettingBinding
import com.example.teamproject.login.AlamActivity
import com.example.teamproject.login.ModProfileActivity
import com.example.teamproject.login.ModifyProfileActivity

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
            val intent = Intent(this@SettingActivity, ModifyProfileActivity::class.java)
            startActivity(intent)
        }

        binding.infomode.setOnClickListener {
            val intent = Intent(this@SettingActivity, ModProfileActivity::class.java)
            startActivity(intent)
        }

        binding.alamset.setOnClickListener {
            val intent = Intent(this@SettingActivity, AlamActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    
}