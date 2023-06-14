package com.example.teamproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamproject.databinding.ActivityMainBinding
import com.example.teamproject.login.LoginActivity
import com.example.teamproject.model.Rstr
import com.example.teamproject.recycler.MyAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var bottommenu: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "메인페이지"

        bottommenu = binding.bottommenu
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottommenu)
        bottomNavigationView.selectedItemId = R.id.first_tab
        
        // 하단바 메뉴 선택 이동 구간
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
        // 메인화면에 스프링에서 받아온 식당리스트를 출력하는 구간
        val userService = (applicationContext as MyApplication).userService
        var getrstrlist = userService.rstrList()

        Log.d("lsy", "${getrstrlist}")
        
        getrstrlist.enqueue(object: Callback<List<Rstr>>{
            override fun onResponse(call: Call<List<Rstr>>, response: Response<List<Rstr>>) {
                if (response.isSuccessful){
                    val rstrL = response.body()
                    Log.d("lsy", "${rstrL}")
                    
                    //받아온 식당정보를 MyAdapter에 넣어서 recyclerView에 넣어주는 작업
                    binding.recyclerView.adapter = MyAdapter(this@MainActivity, rstrL)

                    binding.recyclerView.addItemDecoration(
                        DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))

                }
            }

            override fun onFailure(call: Call<List<Rstr>>, t: Throwable) {
                Log.d("lsy", "실패! ${t.message}")
                call.cancel()
            }

        })


    }
}