package com.example.teamproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamproject.databinding.ActivityMainBinding
import com.example.teamproject.login.LoginActivity
import com.example.teamproject.model.PageListModel
import com.example.teamproject.model.PageListModel2
import com.example.teamproject.recycler.MyAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var bottommenu: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val serviceKey = "DLqEcgtBqy4lMOUw1BMi8NL1H5XiTbNKGuQtPM3epTpiPJZa6jcwQo1DDRmsGstF"

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "메인페이지"

        bottommenu = binding.bottommenu
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottommenu)
        bottomNavigationView.selectedItemId = R.id.first_tab

        binding.bottommenu.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.second_tab -> {
                    Toast.makeText(this@MainActivity, "미구현", Toast.LENGTH_SHORT).show()
                }
                R.id.first_tab -> {
                    Toast.makeText(this@MainActivity, "미구현", Toast.LENGTH_SHORT).show()
                }
                R.id.fourth_tab -> {
                    Toast.makeText(this@MainActivity, "미구현", Toast.LENGTH_SHORT).show()
                }
                R.id.fifth_tab -> {
                    val loginSharedPref = applicationContext.getSharedPreferences("login_prof", Context.MODE_PRIVATE)
                    val userId = loginSharedPref.getString("m_id", null)
                    if ( userId == null){
                        val intent = Intent(this@MainActivity, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this@MainActivity, MyProfilePage::class.java)
                        startActivity(intent)
                    }

                }

            }
            true

        }

        val networkService = (applicationContext as MyApplication).networkService
        val rsListCall = networkService.getList(serviceKey)
        val rsImgListCall = networkService.getImgList(serviceKey)
        Log.d("lsy", "url:" + rsListCall.request().url().toString())
        Log.d("lsy", "url:" + rsImgListCall.request().url().toString())

        rsListCall.enqueue(object : Callback<PageListModel> {

            override fun onResponse(call: retrofit2.Call<PageListModel>, response: Response<PageListModel>) {

                val rsList = response.body()

                Log.d("lsy","rsList data 값 : ${rsList?.body}")
                Log.d("lsy","rsList data 갯수 : ${rsList?.body?.size}")

                rsImgListCall.enqueue(object : Callback<PageListModel2> {

                    override fun onResponse(call: retrofit2.Call<PageListModel2>, response: Response<PageListModel2>) {

                        val rsImgList = response.body()

                        Log.d("lsy","rsImgList data 값 : ${rsImgList?.body}")
                        Log.d("lsy","rsImgList data 갯수 : ${rsImgList?.body?.size}")
                        Log.d("lsy","rsList 이름 : ${rsList?.body?.get(0)?.RSTR_NM}")
                        Log.d("lsy","rsImgList 이름 : ${rsImgList?.body?.get(0)?.RSTR_NM}")
                        for ( i in 0 until (rsList?.body?.size ?: 0)) {
                            var item1 = rsList?.body?.get(i)?.RSTR_NM
                            for ( j in 0 until (rsImgList?.body?.size ?: 0)) {
                                var item2 = rsImgList?.body?.get(j)?.RSTR_NM

                                if ( item1.equals(item2)) {
                                    Log.d("lsy", "rsList 이름 : ${item1}")
                                    Log.d(
                                        "lsy",
                                        "rsImgList 이름 : ${item2}"
                                    )
                                    binding.recyclerView.adapter= MyAdapter(this@MainActivity,rsList?.body, rsImgList?.body)

                                    binding.recyclerView.addItemDecoration(
                                        DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))


                                    continue
                                }
                            }
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<PageListModel2>, t: Throwable) {
                        Log.d("lsy","fail")
                        call.cancel()
                    }


                })
                Log.d("lsy","실행 여부 확인. userListCall.enqueue")



            }


            override fun onFailure(call: retrofit2.Call<PageListModel>, t: Throwable) {
                Log.d("lsy","fail")
                call.cancel()
            }
        })
    }
}