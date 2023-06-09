package com.example.teamproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teamproject.databinding.ActivityMyDiningBinding

class MyDining : AppCompatActivity() {
    lateinit var binding: ActivityMyDiningBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyDiningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            val intent = Intent(this@MyDining, MainActivity::class.java)
            startActivity(intent)
        }
    }
}