package com.example.teamproject

import com.example.teamproject.recycler.MyAdapter
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamproject.databinding.ActivitySearchBinding
import com.example.teamproject.login.LoginActivity
import com.example.teamproject.model.RstrModel
import com.example.teamproject.review.ReviewActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchBinding
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "검색"

        val loginSharedPref = applicationContext.getSharedPreferences("login_prof", Context.MODE_PRIVATE)
        val userId = loginSharedPref.getString("m_id", null)
        val userService = (applicationContext as MyApplication).userService
        // 하단바 초기값 설정
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottommenu)
        bottomNavigationView.selectedItemId = R.id.second_tab

        // 하단바 선택시 이벤티
        binding.bottommenu.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.first_tab -> {
                    val intent = Intent(this@SearchActivity, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.third_tab -> {
                    val intent = Intent(this@SearchActivity, ReviewActivity::class.java)
                    startActivity(intent)
                }
                R.id.fourth_tab -> {
                    if ( userId == null){
                        val intent = Intent(this@SearchActivity, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this@SearchActivity, MyDining::class.java)
                        startActivity(intent)
                    }
                }
                R.id.fifth_tab -> {
                    if ( userId == null){
                        val intent = Intent(this@SearchActivity, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this@SearchActivity, MyProfilePage::class.java)
                        startActivity(intent)
                    }
                }

            }
            true
        }

        binding.searchBtn.setOnClickListener {
            var rstr_nm = binding.searchText.text.toString()
            val getnamecount = userService.getNamecount(rstr_nm)
            getnamecount.enqueue(object: Callback<Int>{
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if ( response.isSuccessful ){
                        val ncount = response.body()
                        if (ncount != null) {
                            if ( ncount >= 1){
                                val getname = userService.getName(rstr_nm)
                                getname.enqueue(object: Callback<List<RstrModel>>{
                                    override fun onResponse(
                                        call: Call<List<RstrModel>>,
                                        response: Response<List<RstrModel>>
                                    ) {
                                        if ( response.isSuccessful ){
                                            val rstrlist = response.body()
                                            binding.recyclerView.adapter = MyAdapter(this@SearchActivity, rstrlist)
                                            binding.recyclerView.addItemDecoration(
                                                DividerItemDecoration(this@SearchActivity, LinearLayoutManager.VERTICAL)
                                            )

                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<List<RstrModel>>,
                                        t: Throwable
                                    ) {
                                        call.cancel()
                                    }

                                })
                            } else {
                                Toast.makeText(this@SearchActivity, "검색 결과가 없습니다!", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    call.cancel()
                }

            })
        }

        binding.area1.setOnClickListener {
            val rstr_addr = binding.area1.text.toString()
            val getarea = userService.getArea(rstr_addr)
            getarea.enqueue(object: Callback<List<RstrModel>>{
                override fun onResponse(
                    call: Call<List<RstrModel>>,
                    response: Response<List<RstrModel>>
                ) {
                    if ( response.isSuccessful ){
                        val arealist = response.body()

                        binding.recyclerView.adapter = MyAdapter(this@SearchActivity, arealist)
                        binding.recyclerView.addItemDecoration(DividerItemDecoration(this@SearchActivity, LinearLayoutManager.VERTICAL))
                    }
                }

                override fun onFailure(call: Call<List<RstrModel>>, t: Throwable) {
                    call.cancel()
                }

            })
        }

        binding.area2.setOnClickListener {
            val rstr_addr = binding.area2.text.toString()
            val getarea = userService.getArea(rstr_addr)
            getarea.enqueue(object: Callback<List<RstrModel>>{
                override fun onResponse(
                    call: Call<List<RstrModel>>,
                    response: Response<List<RstrModel>>
                ) {
                    if ( response.isSuccessful ){
                        val arealist = response.body()

                        binding.recyclerView.adapter = MyAdapter(this@SearchActivity, arealist)
                        binding.recyclerView.addItemDecoration(DividerItemDecoration(this@SearchActivity, LinearLayoutManager.VERTICAL))
                    }
                }

                override fun onFailure(call: Call<List<RstrModel>>, t: Throwable) {
                    call.cancel()
                }

            })
        }

        binding.area3.setOnClickListener {
            val rstr_addr = binding.area3.text.toString()
            val getarea = userService.getArea(rstr_addr)
            getarea.enqueue(object: Callback<List<RstrModel>>{
                override fun onResponse(
                    call: Call<List<RstrModel>>,
                    response: Response<List<RstrModel>>
                ) {
                    if ( response.isSuccessful ){
                        val arealist = response.body()

                        binding.recyclerView.adapter = MyAdapter(this@SearchActivity, arealist)
                        binding.recyclerView.addItemDecoration(DividerItemDecoration(this@SearchActivity, LinearLayoutManager.VERTICAL))
                    }
                }

                override fun onFailure(call: Call<List<RstrModel>>, t: Throwable) {
                    call.cancel()
                }

            })
        }

        binding.area4.setOnClickListener {
            val rstr_addr = binding.area4.text.toString()
            val getarea = userService.getArea(rstr_addr)
            getarea.enqueue(object: Callback<List<RstrModel>>{
                override fun onResponse(
                    call: Call<List<RstrModel>>,
                    response: Response<List<RstrModel>>
                ) {
                    if ( response.isSuccessful ){
                        val arealist = response.body()

                        binding.recyclerView.adapter = MyAdapter(this@SearchActivity, arealist)
                        binding.recyclerView.addItemDecoration(DividerItemDecoration(this@SearchActivity, LinearLayoutManager.VERTICAL))
                    }
                }

                override fun onFailure(call: Call<List<RstrModel>>, t: Throwable) {
                    call.cancel()
                }

            })
        }

        binding.area5.setOnClickListener {
            val rstr_addr = binding.area5.text.toString()
            val getarea = userService.getArea(rstr_addr)
            getarea.enqueue(object: Callback<List<RstrModel>>{
                override fun onResponse(
                    call: Call<List<RstrModel>>,
                    response: Response<List<RstrModel>>
                ) {
                    if ( response.isSuccessful ){
                        val arealist = response.body()

                        binding.recyclerView.adapter = MyAdapter(this@SearchActivity, arealist)
                        binding.recyclerView.addItemDecoration(DividerItemDecoration(this@SearchActivity, LinearLayoutManager.VERTICAL))
                    }
                }

                override fun onFailure(call: Call<List<RstrModel>>, t: Throwable) {
                    call.cancel()
                }

            })
        }

        binding.area6.setOnClickListener {
            val rstr_addr = binding.area6.text.toString()
            val getarea = userService.getArea(rstr_addr)
            getarea.enqueue(object: Callback<List<RstrModel>>{
                override fun onResponse(
                    call: Call<List<RstrModel>>,
                    response: Response<List<RstrModel>>
                ) {
                    if ( response.isSuccessful ){
                        val arealist = response.body()

                        binding.recyclerView.adapter = MyAdapter(this@SearchActivity, arealist)
                        binding.recyclerView.addItemDecoration(DividerItemDecoration(this@SearchActivity, LinearLayoutManager.VERTICAL))
                    }
                }

                override fun onFailure(call: Call<List<RstrModel>>, t: Throwable) {
                    call.cancel()
                }

            })
        }

        binding.area7.setOnClickListener {
            val rstr_addr = binding.area7.text.toString()
            val getarea = userService.getArea(rstr_addr)
            getarea.enqueue(object: Callback<List<RstrModel>>{
                override fun onResponse(
                    call: Call<List<RstrModel>>,
                    response: Response<List<RstrModel>>
                ) {
                    if ( response.isSuccessful ){
                        val arealist = response.body()

                        binding.recyclerView.adapter = MyAdapter(this@SearchActivity, arealist)
                        binding.recyclerView.addItemDecoration(DividerItemDecoration(this@SearchActivity, LinearLayoutManager.VERTICAL))
                    }
                }

                override fun onFailure(call: Call<List<RstrModel>>, t: Throwable) {
                    call.cancel()
                }

            })
        }

        binding.type1.setOnClickListener {
            val rstr_list = binding.type1.text.toString()
            val gettype = userService.getType(rstr_list)
            gettype.enqueue(object: Callback<List<RstrModel>>{
                override fun onResponse(
                    call: Call<List<RstrModel>>,
                    response: Response<List<RstrModel>>
                ) {
                    if ( response.isSuccessful ){
                        val arealist = response.body()

                        binding.recyclerView.adapter = MyAdapter(this@SearchActivity, arealist)
                        binding.recyclerView.addItemDecoration(DividerItemDecoration(this@SearchActivity, LinearLayoutManager.VERTICAL))
                    }
                }

                override fun onFailure(call: Call<List<RstrModel>>, t: Throwable) {
                    call.cancel()
                }

            })
        }

        binding.type2.setOnClickListener {
            val rstr_list = binding.type2.text.toString()
            val gettype = userService.getType(rstr_list)
            gettype.enqueue(object: Callback<List<RstrModel>>{
                override fun onResponse(
                    call: Call<List<RstrModel>>,
                    response: Response<List<RstrModel>>
                ) {
                    if ( response.isSuccessful ){
                        val arealist = response.body()

                        binding.recyclerView.adapter = MyAdapter(this@SearchActivity, arealist)
                        binding.recyclerView.addItemDecoration(DividerItemDecoration(this@SearchActivity, LinearLayoutManager.VERTICAL))
                    }
                }

                override fun onFailure(call: Call<List<RstrModel>>, t: Throwable) {
                    call.cancel()
                }

            })
        }

        binding.type3.setOnClickListener {
            val rstr_list = binding.type3.text.toString()
            val gettype = userService.getType(rstr_list)
            gettype.enqueue(object: Callback<List<RstrModel>>{
                override fun onResponse(
                    call: Call<List<RstrModel>>,
                    response: Response<List<RstrModel>>
                ) {
                    if ( response.isSuccessful ){
                        val arealist = response.body()

                        binding.recyclerView.adapter = MyAdapter(this@SearchActivity, arealist)
                        binding.recyclerView.addItemDecoration(DividerItemDecoration(this@SearchActivity, LinearLayoutManager.VERTICAL))
                    }
                }

                override fun onFailure(call: Call<List<RstrModel>>, t: Throwable) {
                    call.cancel()
                }

            })
        }

        binding.type4.setOnClickListener {
            val rstr_list = binding.type4.text.toString()
            val gettype = userService.getType(rstr_list)
            gettype.enqueue(object: Callback<List<RstrModel>>{
                override fun onResponse(
                    call: Call<List<RstrModel>>,
                    response: Response<List<RstrModel>>
                ) {
                    if ( response.isSuccessful ){
                        val arealist = response.body()

                        binding.recyclerView.adapter = MyAdapter(this@SearchActivity, arealist)
                        binding.recyclerView.addItemDecoration(DividerItemDecoration(this@SearchActivity, LinearLayoutManager.VERTICAL))
                    }
                }

                override fun onFailure(call: Call<List<RstrModel>>, t: Throwable) {
                    call.cancel()
                }

            })
        }

        binding.type5.setOnClickListener {
            val rstr_list = binding.type5.text.toString()
            val gettype = userService.getType(rstr_list)
            gettype.enqueue(object: Callback<List<RstrModel>>{
                override fun onResponse(
                    call: Call<List<RstrModel>>,
                    response: Response<List<RstrModel>>
                ) {
                    if ( response.isSuccessful ){
                        val arealist = response.body()

                        binding.recyclerView.adapter = MyAdapter(this@SearchActivity, arealist)
                        binding.recyclerView.addItemDecoration(DividerItemDecoration(this@SearchActivity, LinearLayoutManager.VERTICAL))
                    }
                }

                override fun onFailure(call: Call<List<RstrModel>>, t: Throwable) {
                    call.cancel()
                }

            })
        }

        binding.type6.setOnClickListener {
            val rstr_list = binding.type6.text.toString()
            val gettype = userService.getType(rstr_list)
            gettype.enqueue(object: Callback<List<RstrModel>>{
                override fun onResponse(
                    call: Call<List<RstrModel>>,
                    response: Response<List<RstrModel>>
                ) {
                    if ( response.isSuccessful ){
                        val arealist = response.body()

                        binding.recyclerView.adapter = MyAdapter(this@SearchActivity, arealist)
                        binding.recyclerView.addItemDecoration(DividerItemDecoration(this@SearchActivity, LinearLayoutManager.VERTICAL))
                    }
                }

                override fun onFailure(call: Call<List<RstrModel>>, t: Throwable) {
                    call.cancel()
                }

            })
        }


    }
}