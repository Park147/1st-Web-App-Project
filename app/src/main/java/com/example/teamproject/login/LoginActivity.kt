package com.example.teamproject.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.teamproject.MainActivity
import com.example.teamproject.MyApplication
import com.example.teamproject.MyProfilePage
import com.example.teamproject.R
import com.example.teamproject.databinding.ActivityLoginBinding
import com.example.teamproject.model.Member
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    var bottommenu: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "로그인"

        bottommenu = binding.bottommenu

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottommenu)
        bottomNavigationView.selectedItemId = R.id.fifth_tab
        
        // 하단바 메뉴 선택시 이벤트
        binding.bottommenu.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.first_tab -> {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.second_tab -> {
                    Toast.makeText(this@LoginActivity, "미구현", Toast.LENGTH_SHORT).show()
                }
                R.id.third_tab -> {
                    Toast.makeText(this@LoginActivity, "미구현", Toast.LENGTH_SHORT).show()
                }
                R.id.fourth_tab -> {
                    Toast.makeText(this@LoginActivity, "미구현", Toast.LENGTH_SHORT).show()

                }

            }
            true
        }

        // 회원가입 페이지로 이동하는 버튼
        binding.membershipbtn.setOnClickListener {
            val intent = Intent(this@LoginActivity, MembershipActivity::class.java)
            startActivity(intent)
        }

        // 로그인 하는 버튼 
        binding.loginbtn.setOnClickListener {
            var m_id = binding.loginid.text.toString()
            var m_password =binding.loginps.text.toString()

            Log.d("logintest1", "${m_id}, ${m_password}")
            
            
            // 여기서부터 로그인과 스프링 연동 구간
            val userService = (applicationContext as MyApplication).userService

            var userLogin = userService.getUser(m_id, m_password)
            Log.d("logintest2", "url:" + userLogin.request().url().toString())
            userLogin.enqueue(object : Callback<Member> {
                override fun onResponse(call: Call<Member>, response: Response<Member>) {
                    if(response.isSuccessful) {
                        val sucUser = response.body()

                        val m_id = sucUser?.m_id.toString()
                        val m_password = sucUser?.m_password.toString()

                        Log.d("logintest3", "${m_id}, ${m_password}")
                        
                        
                        //스프링의 세션과 같은 구간입니다
                        val loginSharedPref = getSharedPreferences("login_prof", Context.MODE_PRIVATE)
                        loginSharedPref.edit().run {
                            putString("m_id", m_id)
                            putString("m_password", m_password)
                            commit()
                        }

                        val intent = Intent(this@LoginActivity, MyProfilePage::class.java)
                        startActivity(intent)

                    }
                }
                override fun onFailure(call: Call<Member>, t: Throwable) {
                    Log.d("logintest4", "실패! ${t.message}")
                    call.cancel()
                }


            })
        }
    }
}