package com.example.teamproject.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.teamproject.MyApplication
import com.example.teamproject.databinding.ActivityModProfileBinding
import com.example.teamproject.model.ModPro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityModProfileBinding

    var logout: Button? = null
    var modinfo: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.title = "내 정보 수정"

        // 이전 페이지에서 intent를 통해서 받아온 값을 출력하는 부분
        var m_id = intent.getStringExtra("m_id")
        var m_name = intent.getStringExtra("m_name")
        var m_phone = intent.getStringExtra("m_phone")
        var m_address = intent.getStringExtra("m_address")
        var m_password = intent.getStringExtra("m_password")

        binding.modname.setText(m_name)
        binding.modphone.setText(m_phone)
        binding.modaddress.setText(m_address)
        binding.modpass.setText(m_password)
        logout = binding.logout
        modinfo = binding.modinfo
        
        
        // 로그아웃 하는 구간입니다.
        binding.logout.setOnClickListener {
            val loginSharedPref = applicationContext.getSharedPreferences("login_prof", Context.MODE_PRIVATE)
            // 스프링의 세션을 비우는 것과 같은 작업
            loginSharedPref.edit().clear().apply()

            val intent = Intent(this@ModProfileActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        
        //내정보를 수정하는 버튼
        binding.modinfo.setOnClickListener {
            var member = ModPro(
                m_id = m_id.toString(),
                m_name = binding.modname.text.toString(),
                m_phone = binding.modphone.text.toString(),
                m_address = binding.modaddress.text.toString(),
                m_password = binding.modpass.text!!.toString()
            )
            Log.d("Modprofile3", "성공 ${member}")
            val userService = ( applicationContext as MyApplication).userService

            var modprofile = userService.mUpPro(member)
            Log.d("Modprofile4", "성공 ${modprofile}")
            modprofile.enqueue(object: Callback<Unit>{
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful){
                        var modpromem = response.body()

                        Log.d("Modprofile1", "성공 ${modpromem}")
                        //이전 페이지로 돌아가는 onBackPressed() 함수
                        onBackPressed()
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("Modprofile2", "실패 ${t.message}")
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