package com.example.teamproject.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teamproject.R
import com.example.teamproject.databinding.ActivityLoginBinding
import com.example.teamproject.databinding.ActivityModifyProfileBinding

class ModifyProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityModifyProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "프로필 수정"
    }
}