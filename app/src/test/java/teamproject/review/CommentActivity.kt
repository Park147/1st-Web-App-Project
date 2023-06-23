package com.example.teamproject.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.k0201_jcy_test.CommentAdapter
import com.example.teamproject.MyApplication
import com.example.teamproject.databinding.ActivityCommentBinding
import com.example.teamproject.model.Comment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentActivity : AppCompatActivity() {
    lateinit var binding: ActivityCommentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "댓글 목록"
        
        val r_num = intent.getIntExtra("r_num", 0)

        val userService = (applicationContext as MyApplication).userService
        val commentListg = userService.cgetList(r_num)
        commentListg.enqueue(object: Callback<List<Comment>>{
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if ( response.isSuccessful ){
                    val comL = response.body()

                    binding.recyclerView.adapter = CommentAdapter(this@CommentActivity, comL)

                    binding.recyclerView.addItemDecoration(
                        DividerItemDecoration(this@CommentActivity, LinearLayoutManager.VERTICAL)
                    )
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                call.cancel()
            }

        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}