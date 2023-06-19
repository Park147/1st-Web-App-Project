package com.example.teamproject.recycler


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.teamproject.MyApplication
import com.example.teamproject.R
import com.example.teamproject.databinding.ItemReviewBinding
import com.example.teamproject.fragment.ReviewFragment
import com.example.teamproject.model.Heart
import com.example.teamproject.model.Review
import com.example.teamproject.retrofit.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReViewHolder(val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root)

class ReviewAdapter(val context: ReviewFragment, val datas:List<Review>?, val userId: String, val userService: UserService): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ReViewHolder(
            ItemReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ReViewHolder).binding


        val review = datas?.get(position)
        binding.tvUploaderName.text = review?.r_id
        binding.tvName.text = review?.r_restaurant
        binding.tvIntroduction.text = review?.r_content
        binding.tvRating.text = review?.r_rating.toString()
        val urlImg = review?.r_picture

        var h_num = review?.r_num?.toInt() ?: 0

        val checkHeart = userService.checkedH(userId, h_num)
        Log.d("Hearttest1","$checkHeart")
        checkHeart.enqueue(object: Callback<Int>{
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if(response.isSuccessful){
                    val hearttype = response.body()
                    Log.d("Hearttest2","${response.body()}")

                    if ( hearttype == 0){
                        binding.ivLike.setImageResource(R.drawable.unheart)
                    }else if ( hearttype == 1){
                        binding.ivLike.setImageResource(R.drawable.inheart)
                    }
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                call.cancel()
            }

        })


//        if (urlImg != null) {
//            Glide.with(context)
//                .load(urlImg)
//                .into(binding.tvImg)
//        }
    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }
}