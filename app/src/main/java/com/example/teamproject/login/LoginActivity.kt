package com.example.teamproject.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teamproject.R
import com.example.teamproject.databinding.ActivityAlamBinding
import com.example.teamproject.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "로그인"
    }
}