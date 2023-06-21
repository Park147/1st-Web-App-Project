package com.example.teamproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.teamproject.databinding.ActivityMyDiningBinding
import com.example.teamproject.fragment.OneFragment
import com.example.teamproject.fragment.TwoFragment
import com.example.teamproject.login.LoginActivity
import com.example.teamproject.review.ReviewActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator

class MyDining : AppCompatActivity() {
    lateinit var binding: ActivityMyDiningBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyDiningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "마이다이닝"

        val bottomN = findViewById<BottomNavigationView>(R.id.bottomMenu)
        bottomN.selectedItemId = R.id.fourth_tab

        val loginSharedPref = applicationContext.getSharedPreferences("login_prof", Context.MODE_PRIVATE)
        val userId = loginSharedPref.getString("m_id", null)

        binding.bottomMenu.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.first_tab -> {
                    val intent = Intent(this@MyDining, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.second_tab -> {
                    val intent = Intent(this@MyDining, SearchActivity::class.java)
                    startActivity(intent)
                }
                R.id.third_tab -> {
                    val intent = Intent(this@MyDining, ReviewActivity::class.java)
                    startActivity(intent)
                }
                R.id.fifth_tab -> {
                    if ( userId == null){
                        val intent = Intent(this@MyDining, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(this@MyDining, MyProfilePage::class.java)
                        startActivity(intent)
                    }
                }
            }
            true
        }

        val tabLayout = binding.tabs

        val viewPager = binding.viewpager


        viewPager.adapter= MyFragmentPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position) {
                0 -> {
                    tab.text = "나의 예약"
                }

                1 -> {
                    tab.text = "나의 알림"
                }
            }
        }.attach()
    }



    class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        val fragments: List<Fragment>
        init {
            fragments= listOf(OneFragment(), TwoFragment())
        }
        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}