package com.example.teamproject.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.teamproject.R
import com.example.teamproject.databinding.ActivityLoginBinding
import com.example.teamproject.databinding.ActivityModProfileBinding

class ModProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityModProfileBinding

    var logout: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.title = "내 정보 수정"

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

        binding.logout.setOnClickListener {
            val loginSharedPref = applicationContext.getSharedPreferences("login_prof", Context.MODE_PRIVATE)
            loginSharedPref.edit().clear().apply()

            val intent = Intent(this@ModProfileActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}