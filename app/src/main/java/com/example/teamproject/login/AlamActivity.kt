package com.example.teamproject.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teamproject.R
import com.example.teamproject.databinding.ActivityAlamBinding
import com.example.teamproject.databinding.ActivityMyProfilePageBinding

class AlamActivity : AppCompatActivity() {
    lateinit var binding: ActivityAlamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "알림 설정"

    }
}