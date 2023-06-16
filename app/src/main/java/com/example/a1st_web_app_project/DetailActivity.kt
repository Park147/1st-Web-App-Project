package com.example.a1st_web_app_project

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.a1st_web_app_project.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val rstr_nm = intent.getStringExtra("rstr_nm")
        val rstr_img = intent.getStringExtra("rstr_img")
        val rstr_addr = intent.getStringExtra("rstr_addr")
        val rstr_tell = intent.getStringExtra("rstr_tell")
        val rstr_intro = intent.getStringExtra("rstr_intro")
        Log.d("test34","${rstr_nm}, ${rstr_img} , ${rstr_addr},${rstr_tell},${rstr_intro}")
        val imgBanner = findViewById<ImageView>(R.id.imageViewImage)
        val tvName = findViewById<TextView>(R.id.textViewName)
        val tvAddress = findViewById<TextView>(R.id.textViewAddress)
        val tvPhoneNumber = findViewById<TextView>(R.id.textViewPhone)
        val tvIntro = findViewById<TextView>(R.id.textViewIntroduction)

        binding.textViewName.text = rstr_nm
        binding.textViewAddress.text = rstr_addr
        binding.textViewPhone.text = rstr_tell
        binding.textViewIntroduction.text = rstr_intro
        Log.d("test54","실패가 뭐고")
        tvName.text = rstr_nm
        tvAddress.text = rstr_addr
        tvPhoneNumber.text = rstr_tell
        tvIntro.text = rstr_intro

        if (rstr_img != null) {
            Glide.with(this)
                .load(rstr_img)
                .into(binding.imageViewImage)
            Log.d("test44","실 뭐고")
        }
    }
}