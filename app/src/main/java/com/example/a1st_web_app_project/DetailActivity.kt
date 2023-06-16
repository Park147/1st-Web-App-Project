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
    private lateinit var textViewName: TextView
    private lateinit var imageViewImage: ImageView
    private lateinit var textViewAddress: TextView
    private lateinit var textViewPhone: TextView
    private lateinit var textViewIntroduction: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        textViewName = findViewById(R.id.textViewName)
        imageViewImage = findViewById(R.id.imageViewImage)
        textViewAddress = findViewById(R.id.textViewAddress)
        textViewPhone = findViewById(R.id.textViewPhone)
        textViewIntroduction = findViewById(R.id.textViewIntroduction)

        val rstrName = intent.getStringExtra("rstr_nm")
        val rstrImage = intent.getStringExtra("rstr_img")
        val rstrAddress = intent.getStringExtra("rstr_addr")
        val rstrPhone = intent.getStringExtra("rstr_tell")
        val rstrIntroduction = intent.getStringExtra("rstr_intro")

        textViewName.text = rstrName
        Glide.with(this).load(rstrImage).into(imageViewImage)
        textViewAddress.text = rstrAddress
        textViewPhone.text = rstrPhone
        textViewIntroduction.text = rstrIntroduction
    }
}