package com.example.teamproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat.startActivity
import com.example.teamproject.databinding.ActivityMyProfilePageBinding
import com.example.teamproject.login.ModifyProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MyProfilePage : AppCompatActivity() {
    lateinit var binding: ActivityMyProfilePageBinding

    var modprofile: Button? = null
    var bottommenu: BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfilePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.title = "마이페이지"

        modprofile = binding.modprofile
        bottommenu = binding.bottommenu

        binding.bottommenu.setOnItemReselectedListener {item ->
            when(item.itemId) {
                R.id.first_tab -> {
                    val intent = Intent(this@MyProfilePage, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.second_tab -> {
                    Toast.makeText(this@MyProfilePage, "미구현", Toast.LENGTH_SHORT).show()
                }
                R.id.third_tab -> {
                    Toast.makeText(this@MyProfilePage, "미구현", Toast.LENGTH_SHORT).show()
                }
                R.id.fourth_tab -> {
                    val intent = Intent(this@MyProfilePage, MyProfilePage::class.java)
                    startActivity(intent)
                }
                R.id.fifth_tab -> {
                    Toast.makeText(this@MyProfilePage, "미구현", Toast.LENGTH_SHORT).show()
                }

            }
            true

        }



        binding.modprofile.setOnClickListener {
            val intent = Intent(this@MyProfilePage, ModifyProfileActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionmenu, menu)
        return true
    }

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
}