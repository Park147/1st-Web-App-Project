package com.example.teamproject.review

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.teamproject.MyApplication
import com.example.teamproject.R
import com.example.teamproject.databinding.ActivityGetReviewBinding
import com.example.teamproject.login.LoginActivity
import com.example.teamproject.model.Follow
import com.example.teamproject.model.Heart
import com.example.teamproject.model.Review
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetReviewActivity : AppCompatActivity() {
    lateinit var binding: ActivityGetReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "리뷰 정보"

        val userService = (applicationContext as MyApplication).userService

        val loginSharedPref = applicationContext.getSharedPreferences("login_prof", Context.MODE_PRIVATE)
        val userId = loginSharedPref.getString("m_id", "").toString()

        var r_num = intent.getIntExtra("r_num", 0)
        var r_id = intent.getStringExtra("r_id")
        var r_restaurant = intent.getStringExtra("r_restaurant")
        var r_content = intent.getStringExtra("r_content")
        var r_rating = intent.getIntExtra("r_rating", 0)


        if (userId == r_id){
            binding.reviewUpdate.setVisibility(View.VISIBLE)
            binding.reviewDelete.setVisibility(View.VISIBLE)
            binding.btnFollow.setVisibility(View.INVISIBLE)
        } else {
            binding.reviewUpdate.setVisibility(View.INVISIBLE)
            binding.reviewDelete.setVisibility(View.INVISIBLE)
            binding.btnFollow.setVisibility(View.VISIBLE)
        }

        binding.reviewUpdate.setOnClickListener {
            val intent = Intent(this@GetReviewActivity, ReviewUpdateActivity::class.java)
            intent.putExtra("r_num", r_num)
            intent.putExtra("r_id", r_id)
            intent.putExtra("r_restaurant", r_restaurant)
            intent.putExtra("r_content", r_content)
            intent.putExtra("r_rating",r_rating)
            startActivity(intent)
        }
        binding.reviewDelete.setOnClickListener {

            val reviewD = userService.viewDelete(r_num)
            reviewD.enqueue(object: Callback<Unit>{
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if ( response.isSuccessful) {
                        Log.d("viewdeletetest", "성공")
                        val intent = Intent(this@GetReviewActivity, ReviewActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    call.cancel()
                }

            })
        }

        binding.rvUploaderName.text = r_id
        binding.rvName.text = r_restaurant
        binding.rvIntroduction.text = r_content
        binding.tvRating.text = r_rating.toString()

        val likenum = userService.heartCount(r_num)
        likenum.enqueue(object: Callback<Int>{
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if ( response.isSuccessful){
                    val hcount = response.body()
                    binding.tvLikeRating.text = hcount?.toString()
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                call.cancel()
            }

        })

        val countComment = userService.commentC(r_num)
        countComment.enqueue(object: Callback<Int>{
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful){
                    val commentnum = response.body()
                    binding.tvCommentcount.text = commentnum.toString()
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                call.cancel()
            }

        })

        val checkFollow = userService.followC(userId, r_id.toString())
        checkFollow.enqueue(object: Callback<Int>{
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful){
                    val follownum = response.body()
                    Log.d("followtest", "$follownum")
                    if ( follownum == 0 ){
                        Log.d("followtest", "$follownum")
                        binding.btnFollow.setTag(R.string.image_follow_name,"followoff")
                        binding.btnFollow.text = "팔로우"
                        binding.btnFollow.setBackgroundResource(R.drawable.button_unfollow)
                        binding.btnFollow.setTextColor(Color.WHITE)
                    } else if ( follownum == 1 ){
                        binding.btnFollow.setTag(R.string.image_follow_name,"followon")
                        binding.btnFollow.text = "팔로잉"
                        binding.btnFollow.setBackgroundResource(R.drawable.button_follow)
                        binding.btnFollow.setTextColor(Color.BLACK)
                    }
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                call.cancel()
            }

        })

        val checkHeart = userService.checkedH(userId, r_num)
        Log.d("Hearttest1","$checkHeart")
        checkHeart.enqueue(object: Callback<Int>{
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if(response.isSuccessful){
                    val hearttype = response.body()
                    Log.d("Hearttest2","${response.body()}")

                    if ( hearttype == 0){
                        binding.ivlike.setImageResource(R.drawable.unheart)
                        binding.ivlike.setTag(R.string.image_like_name, "likeoff")
                    }else if ( hearttype == 1){
                        binding.ivlike.setImageResource(R.drawable.inheart)
                        binding.ivlike.setTag(R.string.image_like_name, "likeon")
                    }
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                call.cancel()
            }

        })


        binding.btnFollow.setOnClickListener {

            if ( userId?.toString() == "null")
            {
                val intent = Intent(this@GetReviewActivity, LoginActivity::class.java)
                startActivity(intent)
            }else {
                Log.d("followtest4","성공")
                val followtype = binding.btnFollow.getTag(R.string.image_follow_name) as String?
                var follow = Follow(
                    fl_id = userId.toString(),
                    fr_id = r_id.toString()
                )
                if (followtype == "followoff") {
                    val followR = userService.followR(follow)
                    followR.enqueue(object : Callback<Unit> {
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            if (response.isSuccessful) {
                                binding.btnFollow.setTag(R.string.image_follow_name,"followon")
                                binding.btnFollow.text = "팔로잉"
                                binding.btnFollow.setBackgroundResource(R.drawable.button_follow)
                                binding.btnFollow.setTextColor(Color.BLACK)
                                Log.d("followtest2","성공")
                            }
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            call.cancel()
                        }

                    })
                } else if ( followtype == "followon"){
                    Log.d("followtest5","성공")
                    val followD = userService.followD(follow)
                    followD.enqueue(object: Callback<Unit>{
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            if ( response.isSuccessful ){
                                binding.btnFollow.setTag(R.string.image_follow_name,"followoff")
                                binding.btnFollow.text = "팔로우"
                                binding.btnFollow.setBackgroundResource(R.drawable.button_unfollow)
                                binding.btnFollow.setTextColor(Color.WHITE)
                                Log.d("followtest3","성공")
                            }
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            Log.d("followtest6","실패 ${t.message}")
                            call.cancel()
                        }

                    })
                }

            }
        }

        binding.ivlike.setOnClickListener {
            Log.d("liketest0","성공 $userId")
            if ( userId?.toString() == "null")
            {
                Log.d("liketest1","로그인 $userId")
                val intent = Intent(this@GetReviewActivity, LoginActivity::class.java)
                startActivity(intent)
            }else {
                val liketype = binding.ivlike.getTag(R.string.image_like_name) as String?
                var heart = Heart(
                    h_id = userId.toString(),
                    h_num = r_num
                )
                if ( liketype == "likeoff")
                {

                    val likeR = userService.heartR(heart)
                    likeR.enqueue(object: Callback<Unit>{
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            if ( response.isSuccessful)
                            {
                                binding.ivlike.setImageResource(R.drawable.inheart)
                                binding.ivlike.setTag(R.string.image_like_name, "likeon")
                                Log.d("liketest3","성공!")
                                val likenum = userService.heartCount(r_num)
                                likenum.enqueue(object: Callback<Int>{
                                    override fun onResponse(call: Call<Int>, response: Response<Int>) {
                                        if ( response.isSuccessful){
                                            val hcount = response.body()
                                            binding.tvLikeRating.text = hcount?.toString()
                                        }
                                    }

                                    override fun onFailure(call: Call<Int>, t: Throwable) {
                                        call.cancel()
                                    }

                                })
                            }
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            call.cancel()
                        }

                    })
                } else if ( liketype == "likeon"){
                    val likeD = userService.heartD(heart)
                    likeD.enqueue(object: Callback<Unit>{
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            if (response.isSuccessful){
                                binding.ivlike.setImageResource(R.drawable.unheart)
                                binding.ivlike.setTag(R.string.image_like_name, "likeoff")
                                Log.d("liketest3","성공!")
                                val likenum = userService.heartCount(r_num)
                                likenum.enqueue(object: Callback<Int>{
                                    override fun onResponse(call: Call<Int>, response: Response<Int>) {
                                        if ( response.isSuccessful){
                                            val hcount = response.body()
                                            binding.tvLikeRating.text = hcount?.toString()
                                        }
                                    }

                                    override fun onFailure(call: Call<Int>, t: Throwable) {
                                        call.cancel()
                                    }

                                })
                            }
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            call.cancel()
                        }

                    })
                }
            }

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}