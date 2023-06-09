package com.example.a1st_web_app_project

import android.os.Bundle
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.a1st_web_app_project.databinding.ActivityMainBinding
import me.relex.circleindicator.CircleIndicator3


class MainActivity : FragmentActivity() {
    lateinit var aPager: ViewPager2
    lateinit var pagerAdapter: FragmentStateAdapter
    lateinit var mIndicator: CircleIndicator3
    lateinit var binding: ActivityMainBinding
    val numPage = 4;
    var Toorbarname: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
              setContentView(binding.root)


        //ViewPager2
        aPager = findViewById<ViewPager2>(R.id.viewpager)
        //Adapter
        pagerAdapter = MyAdapter(this, numPage)
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