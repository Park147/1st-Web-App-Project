package com.example.a1st_web_app_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.a1st_web_app_project.databinding.ActivityMainBinding
import com.example.a1st_web_app_project.fragment.FragmentFirst
import com.example.a1st_web_app_project.fragment.FragmentFourth
import com.example.a1st_web_app_project.fragment.FragmentSecond
import com.example.a1st_web_app_project.fragment.FragmentThird
import com.example.a1st_web_app_project.model.randRstr
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.relex.circleindicator.CircleIndicator3
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : FragmentActivity() {
    lateinit var binding: ActivityMainBinding
    var bottommenu: BottomNavigationView? = null
    var Toorbarname: TextView? = null
    lateinit var viewPager: ViewPager2
    lateinit var indicator: CircleIndicator3

    inner class MyFragmentAdapter(fragmentActivity: FragmentActivity, private val randList: List<randRstr>) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return randList.size
        }

        override fun createFragment(position: Int): Fragment {
            val randRstr = randList[position]
            val bundle = Bundle().apply {
                putString("rstr_nm", randRstr.rstr_nm)
                putString("rstr_img", randRstr.rstr_img)
                putString("rstr_addr", randRstr.rstr_addr)
                putString("rstr_tell", randRstr.rstr_tell)
                putString("rstr_intro", randRstr.rstr_intro)
                putString("rstr_popularity", randRstr.rstr_popularity)
            }

            return when (position) {
                0 -> FragmentFirst().apply { arguments = bundle }
                1 -> FragmentSecond().apply { arguments = bundle }
                2 -> FragmentThird().apply { arguments = bundle }
                3 -> FragmentFourth().apply { arguments = bundle }
                else -> throw IllegalArgumentException("Invalid position: $position")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonStartMainActivity2 = findViewById<Button>(R.id.btn1)
        buttonStartMainActivity2.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intent)
        }
        val buttonStartWaitingActivity = findViewById<Button>(R.id.btn2)
        buttonStartWaitingActivity.setOnClickListener {
            val intent = Intent(this@MainActivity, WaitingActivity::class.java)
            startActivity(intent)
        }
        val buttonStartWaitingActivity3 = findViewById<Button>(R.id.sbtn2)
        buttonStartWaitingActivity3.setOnClickListener {
            val intent = Intent(this@MainActivity, CategoryTwoActivity::class.java)
            startActivity(intent)
        }
        val buttonStartWaitingActivity4 = findViewById<Button>(R.id.sbtn3)
        buttonStartWaitingActivity4.setOnClickListener {
            val intent = Intent(this@MainActivity, CategoryThreeActivity::class.java)
            startActivity(intent)
        }
        val buttonStartWaitingActivity5 = findViewById<Button>(R.id.sbtn1)
        buttonStartWaitingActivity5.setOnClickListener {
            val intent = Intent(this@MainActivity, CategoryOneActivity::class.java)
            startActivity(intent)
        }


        val RstrService = (applicationContext as MyApplication).rstrService

        val getrandlist = RstrService.getRandList()
        getrandlist.enqueue(object : Callback<List<randRstr>> {
            override fun onResponse(call: Call<List<randRstr>>, response: Response<List<randRstr>>) {
                if (response.isSuccessful) {
                    val randList = response.body()
                    Log.d("MainActivity123", "randList: $randList")
                    viewPager = findViewById(R.id.viewpager)
                    indicator = findViewById(R.id.indicator)

                    val fragmentAdapter = randList?.let { MyFragmentAdapter(this@MainActivity, it) }
                    viewPager.adapter = fragmentAdapter
                    indicator.setViewPager(viewPager)

                    // 아이템 클릭 시 DetailActivity로 전환
                    fragmentAdapter?.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                            super.onItemRangeChanged(positionStart, itemCount)
                            viewPager.post {
                                val currentItem = viewPager.currentItem
                                if (currentItem in 0 until itemCount) {
                                    val randRstr = randList[currentItem]
                                    val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                                        putExtra("rstr_nm", randRstr.rstr_nm)
                                        putExtra("rstr_img", randRstr.rstr_img)
                                        putExtra("rstr_addr", randRstr.rstr_addr)
                                        putExtra("rstr_tell", randRstr.rstr_tell)
                                        putExtra("rstr_intro", randRstr.rstr_intro)
                                        putExtra("rstr_popularity", randRstr.rstr_popularity)
                                    }
                                    startActivity(intent)
                                }
                            }
                        }
                    })
                }
            }

            override fun onFailure(call: Call<List<randRstr>>, t: Throwable) {
                Log.d("MainActivity", "Network request failed: ${t.message}")
                call.cancel()
            }
        })
    }
}