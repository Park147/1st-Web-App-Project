package com.example.a1st_web_app_project

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
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

    class MyFragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        val fragments: List<Fragment>
        init {
            fragments= listOf(FragmentFirst(), FragmentSecond(), FragmentThird(), FragmentFourth())
        }

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }

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

        val RstrService = (applicationContext as MyApplication).rstrService

        var getrandlist = RstrService.getRandList();

        getrandlist.enqueue(object: Callback<List<randRstr>>{
            override fun onResponse(
                call: Call<List<randRstr>>,
                response: Response<List<randRstr>>
            ) {
                if (response.isSuccessful){
                    val rand = response.body()
                    Log.d("randlist1", "${rand}")

                    viewPager = findViewById(R.id.viewpager)
                    indicator = findViewById(R.id.indicator)

                    val fragmentAdapter = MyFragmentAdapter(this@MainActivity)
                    viewPager = binding.viewpager
                    viewPager.adapter = fragmentAdapter
                    indicator.setViewPager(viewPager)

                    for (i in 0 until rand?.size!!) {
                        val bundle = Bundle()
                        bundle.putString("rstr_nm", rand?.get(i)?.rstr_nm)
                        Log.d("randlist2", "${rand?.get(i)?.rstr_nm}")
                        bundle.putString("rstr_img", rand?.get(i)?.rstr_img)
                        Log.d("fraglist", "${rand?.get(i)?.rstr_nm} , ${rand?.get(i)?.rstr_img}")
                        val fragment: Fragment = when (i) {
                            0 -> FragmentFirst()
                            1 -> FragmentSecond()
                            2 -> FragmentThird()
                            3 -> FragmentFourth()
                            else -> throw IllegalArgumentException("Invalid position: $i")
                        }asdasd
                        fragment.arguments = bundle
                        supportFragmentManager.beginTransaction().replace(R.id.fragend, fragment).commit()
                    }



                    // ViewPager2 설정

                }
            }

            override fun onFailure(call: Call<List<randRstr>>, t: Throwable) {
                Log.d("randlist2", "실패 ${t.message}")
                call.cancel()
            }

        })



    }




}
