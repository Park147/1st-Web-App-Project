package com.example.teamproject.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.teamproject.MyApplication
import com.example.teamproject.R
import com.example.teamproject.databinding.ActivityLoginBinding
import com.example.teamproject.databinding.ActivityModifyProfileBinding
import com.example.teamproject.model.ModInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModifyProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityModifyProfileBinding

    var modprofiler: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.title = "프로필 수정"

        // 이전 페이지에서 intent를 통해서 받아온 값을 출력하는 부분
        var m_id = intent.getStringExtra("m_id")
        var m_nickname = intent.getStringExtra("m_nickname")
        var m_introduction = intent.getStringExtra("m_introduction")
        var m_activity_area = intent.getStringExtra("m_activity_area")

        binding.modnick.setText(m_nickname)
        binding.modintro.setText(m_introduction)
        binding.modmove.setText(m_activity_area)
        modprofiler = binding.modprofiler

        
        // 프로필을 수정하는 구간
        binding.modprofiler.setOnClickListener {
            var member = ModInfo(
                m_id = m_id.toString(),
                m_nickname = binding.modnick.text.toString(),
                m_introduction = binding.modintro.text.toString(),
                m_activity_area = binding.modmove.text.toString()
            )

            Log.d("Modprofiler1", "성공 ${member}")

            val userService = (applicationContext as MyApplication).userService

            var modprofiler = userService.mUpIntro(member)
            Log.d("Modprofiler2", "성공 ${modprofiler}")

            modprofiler.enqueue(object: Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful){
                        var modpromemb = response.body()

                        Log.d("Modprofiler3", "성공 ${modpromemb}")
                        //이전 페이지로 돌아가는 onBackPressed() 함수
                        onBackPressed()
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("Modprofiler4", "실패 ${t.message}")
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