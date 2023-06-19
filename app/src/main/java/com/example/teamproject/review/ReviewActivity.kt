package com.example.teamproject.review

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.teamproject.MainActivity
import com.example.teamproject.MyProfilePage
import com.example.teamproject.R
import com.example.teamproject.databinding.ActivityReviewBinding
import com.example.teamproject.fragment.ReviewFragment
import com.example.teamproject.login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ReviewActivity : AppCompatActivity() {
    lateinit var binding: ActivityReviewBinding

    var bottommenu: BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "리뷰페이지"

        bottommenu = binding.bottommenu
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottommenu)
        bottomNavigationView.selectedItemId = R.id.third_tab

        val loginSharedPref = applicationContext.getSharedPreferences("login_prof", Context.MODE_PRIVATE)
        val userId = loginSharedPref.getString("m_id", null)

        // 하단바 선택시 이벤티
        binding.bottommenu.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.first_tab -> {
                    val intent = Intent(this@ReviewActivity, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.second_tab -> {
                    Toast.makeText(this@ReviewActivity, "미구현", Toast.LENGTH_SHORT).show()
                }
                R.id.fourth_tab -> {
                    Toast.makeText(this@ReviewActivity, "미구현", Toast.LENGTH_SHORT).show()
                }
                R.id.fifth_tab -> {
                    if ( userId == null){
                        val intent = Intent(this@ReviewActivity, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this@ReviewActivity, MyProfilePage::class.java)
                        startActivity(intent)
                    }

                }

            }
            true
        }

        binding.regView.setOnClickListener {
            if ( userId == null){
                val intent = Intent(this@ReviewActivity, LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@ReviewActivity, RegisterViewActivity::class.java)
                startActivity(intent)
            }
        }

        val viewPager = binding.reviewpager
        viewPager.adapter= ReviewActivity.MyFragmentPagerAdapter(this)


    }

    class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        val fragments: List<Fragment>
        init {
            fragments= listOf(ReviewFragment())
        }
        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}