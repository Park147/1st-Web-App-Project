package com.example.teamproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamproject.databinding.ActivityMainBinding
import com.example.teamproject.model.ItemDataList
import com.example.teamproject.recycler.MyReserveAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: MyReserveAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "메인페이지"

        binding.bottommenu.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.first_tab -> {
                    val intent = Intent(this@MainActivity, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.second_tab -> {
                    Toast.makeText(this@MainActivity, "미구현", Toast.LENGTH_SHORT).show()
                }
                R.id.first_tab -> {
                    Toast.makeText(this@MainActivity, "미구현", Toast.LENGTH_SHORT).show()
                }
                R.id.fourth_tab -> {
                    val intent = Intent(this@MainActivity, MyDining::class.java)
                    startActivity(intent)
                }
                R.id.fifth_tab -> {
                    Toast.makeText(this@MainActivity, "미구현", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }
}