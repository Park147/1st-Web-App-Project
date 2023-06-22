package com.example.teamproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.teamproject.databinding.ActivityDetailBinding
import com.example.teamproject.fragment.OneFragment
import com.example.teamproject.model.ItemData
import com.example.teamproject.model.ItemDataList
import com.example.teamproject.recycler.MyDeleteAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            val intent = Intent(this@DetailActivity, ReserveActivity::class.java)
            intent.putExtra("img", rstr_img)
            intent.putExtra("title", rstr_nm)
            intent.putExtra("addr", rstr_addr)
            intent.putExtra("tell", rstr_tell)
            intent.putExtra("content", rstr_intro)
            intent.putExtra("popularity", rstr_popularity)
            startActivity(intent)
        }

        binding.waiting.setOnClickListener{
            var reserve = ItemData("유저이름",rstr_img,rstr_nm,rstr_intro,rstr_popularity,"","", "","방문예약 웨이팅")

            val networkService = (applicationContext as MyApplication).networkService
            val requestCall = networkService.doInsertReserve(reserve)
            requestCall.enqueue(object : Callback<ItemData> {
                override fun onResponse(call: Call<ItemData>, response: Response<ItemData>) {
                    val intent= Intent(this@DetailActivity,MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<ItemData>, t: Throwable) {
                    call.cancel()
                }

            })

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        Toast.makeText(this@DetailActivity, "뒤로가기", Toast.LENGTH_SHORT).show()
        return super.onSupportNavigateUp()
    }
}