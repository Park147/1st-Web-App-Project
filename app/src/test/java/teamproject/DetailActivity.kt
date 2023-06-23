package com.example.teamproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.teamproject.databinding.ActivityDetailBinding
import com.example.teamproject.login.LoginActivity

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "뒤로가기"

        val loginSharedPref = applicationContext.getSharedPreferences("login_prof", Context.MODE_PRIVATE)
        val userId = loginSharedPref.getString("m_id", null)

        val rstr_nm = intent.getStringExtra("rstr_nm")
        val rstr_img = intent.getStringExtra("rstr_img")
        val rstr_addr = intent.getStringExtra("rstr_addr")
        val rstr_tell = intent.getStringExtra("rstr_tell")
        val rstr_intro = intent.getStringExtra("rstr_intro")
        val rstr_popularity = intent.getStringExtra("rstr_popularity")
        Log.d("test34", "$rstr_nm, $rstr_img, $rstr_addr, $rstr_tell, $rstr_intro, $rstr_popularity")


        binding.textViewName.text = rstr_nm
        binding.textViewAddress.text = rstr_addr
        binding.textViewPhone.text = rstr_tell
        binding.textViewIntroduction.text = rstr_intro
        binding.textViewpopularity.text = "네이버 평점: $rstr_popularity"

        if (rstr_img != null) {
            Glide.with(this)
                .load(rstr_img)
                .into(binding.imageViewImage)
        }

        binding.reserve.setOnClickListener{
            if ( userId == null){
                val intent = Intent(this@DetailActivity, LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@DetailActivity, ReserveActivity::class.java)
                intent.putExtra("img", rstr_img)
                intent.putExtra("title", rstr_nm)
                intent.putExtra("content", rstr_intro)
                startActivity(intent)
            }



        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        Toast.makeText(this@DetailActivity, "뒤로가기", Toast.LENGTH_SHORT).show()
        return super.onSupportNavigateUp()
    }
}