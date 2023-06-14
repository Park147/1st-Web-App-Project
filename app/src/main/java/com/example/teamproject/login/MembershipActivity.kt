package com.example.teamproject.login

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import com.example.teamproject.MyApplication
import com.example.teamproject.R
import com.example.teamproject.databinding.ActivityMembershipBinding
import com.example.teamproject.model.Member
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date

class MembershipActivity : AppCompatActivity() {
    lateinit var binding: ActivityMembershipBinding

    var memid: EditText? = null
    var mempass: EditText? = null
    var mempasscon: EditText? = null
    var memname: EditText? = null
    var memgender: RadioGroup? = null
    var memphone: EditText? = null
    var memaddress: EditText? = null
    var memnick: EditText? = null
    var memintro: EditText? = null
    var memmove: EditText? = null
    var sucmem: Button? = null
    var cGender: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMembershipBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "회원 가입"

        binding.memgender.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.genderM -> cGender = binding.genderM.text?.toString()
                R.id.genderF -> cGender = binding.genderF.text?.toString()
            }
        }

        binding.sucmem.setOnClickListener {
            var datep = Date()
            var member = Member(
                m_id = binding.memid.text.toString(),
                m_password = binding.mempass.text.toString(),
                m_password_con = binding.mempass.text.toString(),
                m_name = binding.memname.text.toString(),
                m_gender = cGender.toString(),
                m_phone = binding.memphone.text.toString(),
                m_address = binding.memaddress.text.toString(),
                m_nickname = binding.memnick.text.toString(),
                m_introduction = binding.memintro.text.toString(),
                m_activity_area = binding.memmove.text.toString(),
                m_created_date = datep.toString()
            )

            val userService = (applicationContext as MyApplication).userService

            var membership = userService.memberregister(member)

            membership.enqueue(object: Callback<Unit>{
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if(response.isSuccessful) {

                        var memship = response.body()

                        Log.d("Regitest1", "성공 ${member}")
                        Log.d("Regitest2", "성공 ${memship}")

                        val intent = Intent(this@MembershipActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("Regitest3", "실패 ${t.message}")
                    call.cancel()
                }


            })
        }

    }
}