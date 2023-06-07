package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    override fun onSupportNavigateUp(): Boolean {
        Toast.makeText(this@MainActivity2,"업버튼 클릭시 동작 확인", Toast.LENGTH_SHORT).show()
        return super.onSupportNavigateUp()
    }
}