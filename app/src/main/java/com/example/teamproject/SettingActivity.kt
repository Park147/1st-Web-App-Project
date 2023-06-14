package com.example.teamproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.teamproject.databinding.ActivitySettingBinding
import com.example.teamproject.login.AlamActivity
import com.example.teamproject.login.LoginActivity
import com.example.teamproject.login.ModProfileActivity
import com.example.teamproject.login.ModifyProfileActivity
import com.example.teamproject.model.Member
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingActivity : AppCompatActivity() {
    lateinit var binding: ActivitySettingBinding

    var modprofileset: Button? = null
    var infomode: Button? = null
    var alamset: Button? = null
    var delmem: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "설정"

        modprofileset = binding.modprofileset
        infomode = binding.infomode
        alamset = binding. alamset
        delmem = binding.delmem
        
        
        //로그인 정보를 가져오는 곳
        val loginSharedPref = applicationContext.getSharedPreferences("login_prof", Context.MODE_PRIVATE)
        val userId = loginSharedPref.getString("m_id", "")
        val password = loginSharedPref.getString("m_password", "")
        
        
        //프로필 설정으로 들어가는 버튼
        binding.modprofileset.setOnClickListener {
            var m_id = userId.toString()

            val userService = (applicationContext as MyApplication).userService

            var userIntro = userService.getIntro(m_id)

            userIntro.enqueue(object: Callback<Member> {
                override fun onResponse(call: Call<Member>, response: Response<Member>) {
                    if(response.isSuccessful) {
                        val sucUser = response.body()

                        val m_id = sucUser?.m_id.toString()
                        val m_nickname = sucUser?.m_nickname.toString()
                        val m_introduction = sucUser?.m_introduction.toString()
                        val m_activity_area = sucUser?.m_activity_area.toString()

                        Log.d("logintest3", "${m_id}, ${m_nickname}, ${m_introduction}, ${m_activity_area}")

                        val intent = Intent(this@SettingActivity, ModifyProfileActivity::class.java)
                        intent.putExtra("m_id", m_id)
                        intent.putExtra("m_nickname", m_nickname)
                        intent.putExtra("m_introduction", m_introduction)
                        intent.putExtra("m_activity_area", m_activity_area)
                        startActivity(intent)

                    }
                }

                override fun onFailure(call: Call<Member>, t: Throwable) {
                    call.cancel()
                }

            })
        }
        
        // 내정보 수정으로 들어가는 버튼
        binding.infomode.setOnClickListener {
            var m_id = userId.toString()

            val userService = (applicationContext as MyApplication).userService

            var userModify = userService.getPro(m_id)

            userModify.enqueue(object: Callback<Member> {
                override fun onResponse(call: Call<Member>, response: Response<Member>) {
                    if(response.isSuccessful) {
                        val sucUser = response.body()

                        val m_id = sucUser?.m_id.toString()
                        val m_name = sucUser?.m_name.toString()
                        val m_phone = sucUser?.m_phone.toString()
                        val m_address = sucUser?.m_address.toString()
                        val m_password = sucUser?.m_password.toString()

                        Log.d("logintest3", "${m_id}, ${m_name}, ${m_phone}, ${m_address}, ${m_password}")

                        val intent = Intent(this@SettingActivity, ModProfileActivity::class.java)
                        intent.putExtra("m_id", m_id)
                        intent.putExtra("m_name", m_name)
                        intent.putExtra("m_phone", m_phone)
                        intent.putExtra("m_address", m_address)
                        intent.putExtra("m_password", m_password)
                        startActivity(intent)

                    }
                }

                override fun onFailure(call: Call<Member>, t: Throwable) {
                    call.cancel()
                }

            })
        }
        
        // 알람 설정으로 들어가는 버튼
        binding.alamset.setOnClickListener {
            val intent = Intent(this@SettingActivity, AlamActivity::class.java)
            startActivity(intent)
        }
        // 회원 탈퇴를 위한 버튼
        binding.delmem.setOnClickListener {
            var m_id = userId.toString()

            val userService = (applicationContext as MyApplication).userService
            
            //탈퇴를 위해 설정한 서비스가져오기
            var deluser = userService.memberDelete(m_id)

            deluser.enqueue(object: Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if(response.isSuccessful) {
                        val sucUser = response.body()

                        //로그인 정보를 비워버리는 작업 [ 세션 지우기와 같음 ]
                        val loginSharedPref = applicationContext.getSharedPreferences("login_prof", Context.MODE_PRIVATE)
                        loginSharedPref.edit().clear().apply()
                        
                        //로그인 정보를 비웠기에 로그인 페이지로 돌아가는 작업
                        val intent = Intent(this@SettingActivity, LoginActivity::class.java)
                        startActivity(intent)
                        Log.d("deletetest1", "성공 LoginActivity로 이동")


                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("deletetest2", "실패 ${t.message}")
                    call.cancel()
                }

            })
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    
}