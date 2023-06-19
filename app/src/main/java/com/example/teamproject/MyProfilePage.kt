package com.example.teamproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.teamproject.databinding.ActivityMyProfilePageBinding
import com.example.teamproject.fragment.BookmarkFragment
import com.example.teamproject.fragment.ReviewFragment
import com.example.teamproject.login.ModProfileActivity
import com.example.teamproject.model.Member
import com.example.teamproject.model.Pimg
import com.example.teamproject.review.ReviewActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyProfilePage : AppCompatActivity() {
    lateinit var binding: ActivityMyProfilePageBinding
    var modprofile: Button? = null
    var bottommenu: BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfilePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        //액션바 설정
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "마이페이지"

        modprofile = binding.modprofile
        bottommenu = binding.bottommenu




        // 하단바 초기값 설정
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottommenu)
        bottomNavigationView.selectedItemId = R.id.fifth_tab
        
        // 하단바 선택시 이벤티
        binding.bottommenu.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.first_tab -> {
                    val intent = Intent(this@MyProfilePage, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.second_tab -> {
                    Toast.makeText(this@MyProfilePage, "미구현", Toast.LENGTH_SHORT).show()
                }
                R.id.third_tab -> {
                    val intent = Intent(this@MyProfilePage, ReviewActivity::class.java)
                    startActivity(intent)
                }
                R.id.fourth_tab -> {
                    Toast.makeText(this@MyProfilePage, "미구현", Toast.LENGTH_SHORT).show()

                }

            }
            true
        }

        // 프로필 수정으로 돌아가는 작업
        binding.modprofile.setOnClickListener {
            
            // 정보를 뽑기위해 m_id의 값을 들고오는 작업
            var m_id = binding.userId.text.toString()

            val userService = (applicationContext as MyApplication).userService

            var userModify = userService.getPro(m_id)

            userModify.enqueue(object: Callback<Member> {
                override fun onResponse(call: Call<Member>, response: Response<Member>) {
                    if(response.isSuccessful) {
                        val sucUser = response.body()
                        
                        // body에서 쓸 아이템만 가져오기 위해 따로 변수로 선언하는 곳
                        val m_id = sucUser?.m_id.toString()
                        val m_name = sucUser?.m_name.toString()
                        val m_phone = sucUser?.m_phone.toString()
                        val m_address = sucUser?.m_address.toString()
                        val m_password = sucUser?.m_password.toString()

                        Log.d("logintest3", "${m_id}, ${m_name}, ${m_phone}, ${m_address}, ${m_password}")

                        // activity를 넘어갈때 값을 가져가는 구간
                        val intent = Intent(this@MyProfilePage, ModProfileActivity::class.java)
                        intent.putExtra("m_id", m_id)
                        intent.putExtra("m_name", m_name)
                        intent.putExtra("m_phone", m_phone)
                        intent.putExtra("m_address", m_address)
                        intent.putExtra("m_password", m_password)
                        startActivity(intent)

                    }
                }

                override fun onFailure(call: Call<Member>, t: Throwable) {
                    call.cancel()
                }

            })


        }
        
        // 로그인 정보를 가져오는 곳
        val loginSharedPref = applicationContext.getSharedPreferences("login_prof", Context.MODE_PRIVATE)
        val userId = loginSharedPref.getString("m_id", "").toString()
        val password = loginSharedPref.getString("m_password", "")

        binding.userId.text = userId

//        val userService = ( applicationContext as MyApplication).userService
//
//        val usersel = userService.selimg(userId)
//
//        Log.d("galtest1", "${usersel.request().url()}")
//
//        usersel.enqueue(object: Callback<Pimg>{
//            override fun onResponse(call: Call<Pimg>, response: Response<Pimg>) {
//                if (response.isSuccessful){
//                    val userprofileimg = response.body()
//                    val userimg = userprofileimg?.img
//                    Log.d("galtest1","$userprofileimg")
//                    Log.d("galtest1","성공 ")
//                    if (userimg != null) {
//                        val bitmap = StringToBitmaps(userimg)
//                        Log.d("galtest1", "$bitmap")
//                    }
//
//                    // String -> Bitmap 변환
//
//
//
//
//                }
//            }
//
//            override fun onFailure(call: Call<Pimg>, t: Throwable) {
//                Log.d("selimg2","실패 ${t.message}")
//                call.cancel()
//            }
//
//        })

        val tabLayout = binding.tabs
        val viewPager = binding.viewpager
        viewPager.adapter= MyFragmentPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position) {
                0 -> {
                    tab.text = "즐겨찾기"
                }

                1 -> {
                    tab.text = "리뷰"
                }
            }
        }.attach()




    }

    class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        val fragments: List<Fragment>
        init {
            fragments= listOf(BookmarkFragment(), ReviewFragment())
        }
        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }
    //액션바 메뉴 설정
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionmenu, menu)
        return true
    }

    //액션바 메뉴 클릭시
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.action_setting -> {
                val intent = Intent(this@MyProfilePage, SettingActivity::class.java)
                startActivity(intent)
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

//    fun StringToBitmaps(image: String): Bitmap? {
//        return try {
//            val encodeByte = Base64.decode(image, Base64.DEFAULT)
//            Log.d("galtest2 i","${encodeByte}")
//            Log.d("galtest2 size","${encodeByte.size}")
//            val bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
//            Log.d("galtest2","$bitmap")
//            bitmap
//        } catch (e: Exception) {
//            Log.d("galtest2","${e.message}")
//            e.printStackTrace()
//            null
//        }
//    }

}