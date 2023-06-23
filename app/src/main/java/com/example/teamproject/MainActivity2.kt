package com.example.teamproject

import com.example.teamproject.recycler.MyAdapter
import com.example.teamproject.recycler.MyAdapterListener
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamproject.databinding.ActivityMain2Binding
import com.example.teamproject.model.RstrModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity(), MyAdapterListener {
    lateinit var binding: ActivityMain2Binding
    private lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "뒤로가기"

        binding.bottommenu.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.first_tab -> {
                }
                R.id.second_tab -> {
                    Toast.makeText(this@MainActivity2, "미구현", Toast.LENGTH_SHORT).show()

                }
                R.id.first_tab -> {
                    Toast.makeText(this@MainActivity2, "미구현", Toast.LENGTH_SHORT).show()
                }
                R.id.fourth_tab -> {
                    val intent = Intent(this@MainActivity2, MyDining::class.java)
                    startActivity(intent)
                }
                R.id.fifth_tab -> {
                    Toast.makeText(this@MainActivity2, "미구현", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

        val rstrService = (applicationContext as MyApplication).userService

        myAdapter = MyAdapter(this, null)
        myAdapter.setListener(this)
        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        val getRstrList = rstrService.getRstrList()
        Log.d("jcy", "${getRstrList.request().url().toString()}")

        getRstrList.enqueue(object : Callback<List<RstrModel>> {
            override fun onResponse(
                call: Call<List<RstrModel>>,
                response: Response<List<RstrModel>>
            ) {
                if (response.isSuccessful) {
                    val rstrList = response.body()
                    Log.d("jcy", "$rstrList")
                    if (rstrList != null) {
                        myAdapter.updateDatas(rstrList)
                    }
                }
            }

            override fun onFailure(call: Call<List<RstrModel>>, t: Throwable) {
                Log.d("jcy", "실패 ${t.message}")
                call.cancel()
            }
        })
    }

    override fun onItemClick(data: RstrModel) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("rstr_nm", data.rstr_nm)
        intent.putExtra("rstr_img", data.rstr_img)
        intent.putExtra("rstr_addr", data.rstr_addr)
        intent.putExtra("rstr_tell", data.rstr_tell)
        intent.putExtra("rstr_intro", data.rstr_intro)
        intent.putExtra("rstr_popularity", data.rstr_popularity)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}