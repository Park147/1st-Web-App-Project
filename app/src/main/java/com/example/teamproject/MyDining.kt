package com.example.teamproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.teamproject.databinding.ActivityMyDiningBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator

class MyDining : AppCompatActivity() {
    lateinit var binding: ActivityMyDiningBinding

    var bottommenu: BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyDiningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "마이다이닝"

        val bottomN = findViewById<BottomNavigationView>(R.id.bottomMenu)
        bottomN.selectedItemId = R.id.fourth_tab

        binding.bottomMenu.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.first_tab -> {
                    val intent = Intent(this@MyDining, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.second_tab -> {
                    Toast.makeText(this@MyDining, "미구현", Toast.LENGTH_SHORT).show()
                }
                R.id.first_tab -> {
                    Toast.makeText(this@MyDining, "미구현", Toast.LENGTH_SHORT).show()
                }
                R.id.fourth_tab -> {

                }
                R.id.fifth_tab -> {
                    Toast.makeText(this@MyDining, "미구현", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

        val tabLayout = binding.tabs

        //뷰 페이저 2 추가 .-> xml 에서 , viewpager 부분을 추가해야함.
        val viewPager = binding.viewpager

        // 뷰페이져2 부분에 설정 한 부분 적용하기. 어댑터 연결.
        // 어댑터 , 뷰 객체에 데이터를 연결(연동) 하는 부분.
        viewPager.adapter= MyFragmentPagerAdapter(this)

        // 탭 부분과 뷰페이저2 연동하기.
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