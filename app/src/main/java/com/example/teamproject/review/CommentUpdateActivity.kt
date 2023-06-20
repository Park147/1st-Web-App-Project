package com.example.teamproject.review

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teamproject.MyApplication
import com.example.teamproject.R
import com.example.teamproject.databinding.ActivityCommentUpdateBinding
import com.example.teamproject.model.CommentU
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date

class CommentUpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityCommentUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "댓글 수정"

        val c_num = intent.getIntExtra("c_num", 0)
        val c_id = intent.getStringExtra("c_id")
        val c_content = intent.getStringExtra("c_content")
        val c_getnum = intent.getIntExtra("c_getnum", 0)

        binding.edtComment.setText(c_content)

        binding.btnCommentConfirm.setOnClickListener {
            val date = Date()
            var commentU = CommentU(
                c_num = c_num,
                c_content = binding.edtComment.text.toString(),
                c_modifydate = date.toString()
            )
            val userService = ( applicationContext as MyApplication).userService
            val comU = userService.commentU(commentU)
            comU.enqueue(object: Callback<Unit>{
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if ( response.isSuccessful ){
                        val intent = Intent(this@CommentUpdateActivity, CommentActivity::class.java)
                        intent.putExtra("r_num", c_getnum)
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