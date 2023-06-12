package com.example.a1st_web_app_project

import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.a1st_web_app_project.adapter.FragmentAdapter
import com.example.a1st_web_app_project.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.relex.circleindicator.CircleIndicator3


class MainActivity : FragmentActivity() {
    lateinit var aPager: ViewPager2
    lateinit var pagerAdapter: FragmentStateAdapter
    lateinit var mIndicator: CircleIndicator3
    lateinit var binding: ActivityMainBinding
    val numPage = 4;
    var bottommenu: BottomNavigationView? = null
    var Toorbarname: TextView? = null

ㅋㅋㅋ
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn2.setOnClickListener {
            val intent = Intent(this@MainActivity,WaitingActivity::class.java)
            startActivity(intent);
        }

        binding.btn1.setOnClickListener {
            val intent = Intent(this@MainActivity,MainActivity2::class.java)
            startActivity(intent);
        }

        bottommenu = binding.bottommenu
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottommenu)
        bottomNavigationView.selectedItemId = R.id.first_tab

        binding.bottommenu.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.second_tab -> {
                    Toast.makeText(this@MainActivity, "미구현", Toast.LENGTH_SHORT).show()
                }
                R.id.first_tab -> {
                    Toast.makeText(this@MainActivity, "미구현", Toast.LENGTH_SHORT).show()
                }
                R.id.fourth_tab -> {
                    Toast.makeText(this@MainActivity, "미구현", Toast.LENGTH_SHORT).show()
                }
                R.id.fifth_tab -> {
                    val intent = Intent(this@MainActivity, MainActivity2::class.java)
                    startActivity(intent)
                }

            }
            true

        }

        //ViewPager2
        aPager = findViewById<ViewPager2>(R.id.viewpager)
        //Adapter
        pagerAdapter = FragmentAdapter(this, numPage)
        aPager.setAdapter(pagerAdapter)
        //Indicator
        mIndicator = findViewById<CircleIndicator3>(R.id.indicator)
        mIndicator.setViewPager(aPager)
        mIndicator.createIndicators(numPage, 0)
        //ViewPager Setting
        aPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL)
        aPager.setCurrentItem(1000)
        aPager.setOffscreenPageLimit(3)
        aPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (positionOffsetPixels == 0) {
                    aPager.setCurrentItem(position)
                }
            }
            // 프래그먼트
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mIndicator.animatePageSelected(position % numPage)
            }
        })
        val pageMargin = resources.getDimensionPixelOffset(R.dimen.pageMargin).toFloat()
        val pageOffset = resources.getDimensionPixelOffset(R.dimen.offset).toFloat()
        aPager.setPageTransformer(ViewPager2.PageTransformer { page, position ->
            val myOffset = position * -(2 * pageOffset + pageMargin)
            if (aPager.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(aPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.translationX = -myOffset
                } else {
                    page.translationX = myOffset
                }
            } else {
                page.translationY = myOffset
            }
        })
    }
}

//class MainActivity : AppCompatActivity() {
//    lateinit var binding: ActivityMainBinding
//
//    var Toorbarname: TextView? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setSupportActionBar(binding.toolbar)
//        supportActionBar?.setDisplayShowTitleEnabled(false)
//        binding.toolbar.title = "main"
//
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.actionmenu, menu)
//        return true
//    }
//
//}