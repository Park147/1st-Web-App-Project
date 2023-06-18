package com.example.a1st_web_app_project

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.a1st_web_app_project.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "뒤로가기"

        val rstr_nm = intent.getStringExtra("rstr_nm")
        val rstr_img = intent.getStringExtra("rstr_img")
        val rstr_addr = intent.getStringExtra("rstr_addr")
        val rstr_tell = intent.getStringExtra("rstr_tell")
        val rstr_intro = intent.getStringExtra("rstr_intro")
        Log.d("test34","${rstr_nm}, ${rstr_img} , ${rstr_addr},${rstr_tell},${rstr_intro}")


        binding.textViewName.text = rstr_nm
        binding.textViewAddress.text = rstr_addr
        binding.textViewPhone.text = rstr_tell
        binding.textViewIntroduction.text = rstr_intro
        Log.d("test54","실패가 뭐고")


        if (rstr_img != null) {
            Glide.with(this)
                .load(rstr_img)
                .into(binding.imageViewImage)
            Log.d("test44","실 뭐고")
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        Toast.makeText(this@DetailActivity, "뒤로가기", Toast.LENGTH_SHORT).show()
        return super.onSupportNavigateUp()
    }
}