package com.example.teamproject.bookmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.NonNull
import com.example.teamproject.R
import com.example.teamproject.databinding.ActivityBookMarkBinding

class BookMarkActivity : AppCompatActivity() {
    lateinit var binding: ActivityBookMarkBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookMarkBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}