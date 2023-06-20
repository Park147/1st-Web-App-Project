package com.example.teamproject.review

import android.content.Context
import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.teamproject.MyApplication
import com.example.teamproject.R
import com.example.teamproject.databinding.ActivityRegisterViewBinding
import com.example.teamproject.model.Review
import com.example.teamproject.model.Review_r
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date

class RegisterViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterViewBinding
    var ran: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "리뷰 등록"

        val loginSharedPref = applicationContext.getSharedPreferences("login_prof", Context.MODE_PRIVATE)
        val userId = loginSharedPref.getString("m_id", "")

        binding.rvUploaderName.text = userId

        binding.rating.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId){
                R.id.rone -> ran = 1
                R.id.rtwo -> ran = 2
                R.id.rthree -> ran = 3
                R.id.rfour -> ran = 4
                R.id.rfive -> ran = 5
            }
        }

        binding.reviewregister.setOnClickListener {

            val date = Date()
            var review = Review_r(
                r_restaurant = binding.rvName.text.toString(),
                r_id = userId.toString(),
                r_picture = "1",
                r_content = binding.rvIntroduction.text.toString(),
                r_registerdate = date.toString(),
                r_rating = ran,
                r_modifydate = date.toString()
            )

            val userService = (applicationContext as MyApplication).userService

            val reviewR = userService.viewRg(review)
            reviewR.enqueue(object: Callback<Unit>{
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if(response.isSuccessful){
                        Log.d("regtest", "성공")
                        val intent = Intent(this@RegisterViewActivity, ReviewActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("regtest", "실패 ${t.message}")
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