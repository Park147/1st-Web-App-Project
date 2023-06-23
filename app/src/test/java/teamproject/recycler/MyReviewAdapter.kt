package com.example.teamproject.recycler


import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.teamproject.R
import com.example.teamproject.databinding.ItemReviewBinding
import com.example.teamproject.fragment.MyReviewFragment
import com.example.teamproject.fragment.ReviewFragment
import com.example.teamproject.login.LoginActivity
import com.example.teamproject.model.CommentR
import com.example.teamproject.model.Follow
import com.example.teamproject.model.Heart
import com.example.teamproject.model.Review
import com.example.teamproject.retrofit.UserService
import com.example.teamproject.review.CommentActivity
import com.example.teamproject.review.GetReviewActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date

class MyReViewHolder(val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root)

class MyReviewAdapter(val context: MyReviewFragment, val datas:List<Review>?, val userId: String, val userService: UserService): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyReViewHolder(
            ItemReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyReViewHolder).binding


        val review = datas?.get(position)
        binding.tvUploaderName.text = review?.r_id
        binding.tvName.text = review?.r_restaurant
        binding.tvIntroduction.text = review?.r_content
        binding.tvRating.text = review?.r_rating.toString()
        val urlImg = review?.r_picture

        //        if (urlImg != null) {
//            Glide.with(context)
//                .load(urlImg)
//                .into(binding.tvImg)
//        }
        var r_num = review?.r_num?.toInt() ?: 0

        if ( userId == review?.r_id ) {
            binding.btnFollow.setVisibility(View.INVISIBLE)
        } else {
            binding.btnFollow.setVisibility(View.VISIBLE)
        }

        binding.ivComment.setOnClickListener {
            val intent = Intent(context?.requireContext(), CommentActivity::class.java)
            intent.putExtra("r_num", r_num)
            context.startActivity(intent)
        }

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

        val checkFollow = userService.followC(userId, review?.r_id.toString())
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

        binding.btnCommentConfirm.setOnClickListener {
            Log.d("commenttest1","로그인 $userId")
            if ( userId?.toString() == "null")
            {
                val intent = Intent(context?.requireContext(), LoginActivity::class.java)
                context.startActivity(intent)
            }else {
                val date = Date()
                var commentR = CommentR(
                    c_id = userId.toString(),
                    c_getnum = r_num,
                    c_content = binding.edtComment.text!!.toString(),
                    c_registerdate = date.toString(),
                    c_modifydate = date.toString()
                )

                val commentRg = userService.commentR(commentR)
                commentRg.enqueue(object: Callback<Unit>{
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        if (response.isSuccessful){
                            Log.d("commenttest2", "성공!")
                            binding.edtComment.text = null
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

                            val intent = Intent(context?.requireContext(), CommentActivity::class.java)
                            intent.putExtra("r_num", r_num)
                            context.startActivity(intent)
                        }
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        Log.d("commenttest3", "실패 ${t.message}")
                        call.cancel()
                    }

                })
            }
        }

        binding.btnFollow.setOnClickListener {

            if ( userId?.toString() == "null")
            {
                val intent = Intent(context?.requireContext(), LoginActivity::class.java)
                context.startActivity(intent)
            }else {
            Log.d("followtest4","성공")
                val followtype = binding.btnFollow.getTag(R.string.image_follow_name) as String?
                var follow = Follow(
                    fl_id = userId.toString(),
                    fr_id = review?.r_id.toString()
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
                val intent = Intent(context?.requireContext(), LoginActivity::class.java)
                context.startActivity(intent)
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

        binding.getReview.setOnClickListener {
            val getReview = userService.viewGet(r_num)
            getReview.enqueue(object: Callback<Review>{
                override fun onResponse(call: Call<Review>, response: Response<Review>) {
                    if ( response.isSuccessful) {
                        val getR = response.body()

                        val intent = Intent(context?.requireContext(), GetReviewActivity::class.java)
                        intent.putExtra("r_num", getR?.r_num)
                        intent.putExtra("r_id", getR?.r_id)
                        intent.putExtra("r_restaurant", getR?.r_restaurant)
                        intent.putExtra("r_content", getR?.r_content)
                        intent.putExtra("r_rating",getR?.r_rating)
                        context.startActivity(intent)

                    }
                }

                override fun onFailure(call: Call<Review>, t: Throwable) {
                    call.cancel()
                }

            })


        }



    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }
}