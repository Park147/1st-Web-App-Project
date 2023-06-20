package com.example.teamproject.review

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teamproject.MyApplication
import com.example.teamproject.R
import com.example.teamproject.databinding.ActivityReviewUpdateBinding
import com.example.teamproject.model.ReviewU
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date

class ReviewUpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityReviewUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "리뷰 수정"

        val userService = (applicationContext as MyApplication).userService

        val loginSharedPref = applicationContext.getSharedPreferences("login_prof", Context.MODE_PRIVATE)
        val userId = loginSharedPref.getString("m_id", "").toString()

        var r_num = intent.getIntExtra("r_num", 0)
        var r_id = intent.getStringExtra("r_id")
        var r_restaurant = intent.getStringExtra("r_restaurant")
        var r_content = intent.getStringExtra("r_content")
        var r_rating = intent.getIntExtra("r_rating", 0)

        binding.rvUploaderName.text = r_id
        binding.rvName.text = r_restaurant
        binding.rvIntroduction.setText(r_content)
        binding.tvRating.text = r_rating.toString()

        binding.reviewUpdate.setOnClickListener {
            val date = Date()
            var reviewU = ReviewU(
                r_num = r_num,
                r_picture = "1",
                r_content = binding.rvIntroduction.text.toString(),
                r_modifydate = date.toString()
            )
            val reviewUpdate = userService.viewUpdate(reviewU)
            reviewUpdate.enqueue(object: Callback<Unit>{
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful){
                        val upreview = response.body()

                        val intent = Intent(this@ReviewUpdateActivity, GetReviewActivity::class.java)
                        intent.putExtra("r_num", r_num)
                        intent.putExtra("r_id", r_id)
                        intent.putExtra("r_restaurant", r_restaurant)
                        intent.putExtra("r_content", binding.rvIntroduction.text.toString())
                        intent.putExtra("r_rating",r_rating)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
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