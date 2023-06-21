package com.example.teamproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.teamproject.databinding.ActivityReserveBinding
import com.example.teamproject.model.BlankItem
import com.example.teamproject.model.ItemData
import com.google.android.material.datepicker.MaterialDatePicker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class ReserveActivity : AppCompatActivity() {
    lateinit var binding: ActivityReserveBinding
    var date:Long = 0L
    var reserveDate:String = ""
    var reserveTime:String = ""
    var personCount:String = ""
    var img:String? = ""
    var title:String? = ""
    var content:String? = ""
    var addr:String? = ""
    var tell:String? = ""
    var popularity:String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReserveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //날짜 선택하는 달력 호출
        binding.dateButton.setOnClickListener {
            showDatePicker()
        }

        // 예약날짜, 인원수 입력안하면 Toast호출, 예약시간 클릭하면 db에 값 저장하고 메인으로 넘어감
        binding.btn1.setOnClickListener {

            reserveDate = binding.dateButton.text.toString()
            reserveTime = binding.btn1.text.toString()
            personCount = binding.person.text.toString()
            img = intent.getStringExtra("img")
            title = intent.getStringExtra("title")
            content = intent.getStringExtra("content")
            addr = intent.getStringExtra("addr")
            tell = intent.getStringExtra("tell")
            popularity = intent.getStringExtra("popularity")

            if(reserveDate == "") {
                Toast.makeText(this, "날짜를 입력하시오", Toast.LENGTH_SHORT).show()
            } else if(personCount == "") {
                Toast.makeText(this, "인원수를 입력하시오", Toast.LENGTH_SHORT).show()
            } else {
                var reserve = ItemData("유저이름",img,title,content,"",reserveTime,reserveDate, personCount,"방문예약")

                val networkService = (applicationContext as MyApplication).networkService
                val requestCall = networkService.doInsertReserve(reserve)
                requestCall.enqueue(object : Callback<ItemData> {
                    override fun onResponse(call: Call<ItemData>, response: Response<ItemData>) {
                        val intent= Intent(this@ReserveActivity,MainActivity::class.java)
                        startActivity(intent)
                    }

                    override fun onFailure(call: Call<ItemData>, t: Throwable) {
                        call.cancel()
                    }

                })
            }
        }

        // 예약날짜, 인원수 입력안하면 Toast호출, 예약시간 클릭하면 db에 값 저장하고 메인으로 넘어감
        binding.btn2.setOnClickListener {

            reserveDate = binding.dateButton.text.toString()
            reserveTime = binding.btn2.text.toString()
            personCount = binding.person.text.toString()
            img = intent.getStringExtra("img")
            Log.d("lmj", "이미지 파일 : $img")
            title = intent.getStringExtra("title")
            content = intent.getStringExtra("content")

            if(reserveDate == "") {
                Toast.makeText(this, "날짜를 입력하시오", Toast.LENGTH_SHORT).show()
            } else if(personCount == "") {
                Toast.makeText(this, "인원수를 입력하시오", Toast.LENGTH_SHORT).show()
            } else {
                var reserve = ItemData("유저이름",img,title,content,"",reserveTime,reserveDate, personCount,"방문예약")

                val networkService = (applicationContext as MyApplication).networkService
                val requestCall = networkService.doInsertReserve(reserve)
                requestCall.enqueue(object : Callback<ItemData> {
                    override fun onResponse(call: Call<ItemData>, response: Response<ItemData>) {
                        val intent= Intent(this@ReserveActivity,MainActivity::class.java)
                        startActivity(intent)
                    }

                    override fun onFailure(call: Call<ItemData>, t: Throwable) {
                        call.cancel()
                    }

                })
            }
        }

        // 예약날짜, 인원수 입력안하면 Toast호출, 예약시간 클릭하면 db에 값 저장하고 메인으로 넘어감
        binding.btn3.setOnClickListener {

            reserveDate = binding.dateButton.text.toString()
            reserveTime = binding.btn3.text.toString()
            personCount = binding.person.text.toString()
            img = intent.getStringExtra("img")
            Log.d("lmj", "이미지 파일 : $img")
            title = intent.getStringExtra("title")
            content = intent.getStringExtra("content")

            if(reserveDate == "") {
                Toast.makeText(this, "날짜를 입력하시오", Toast.LENGTH_SHORT).show()
            } else if(personCount == "") {
                Toast.makeText(this, "인원수를 입력하시오", Toast.LENGTH_SHORT).show()
            } else {
                var reserve = ItemData("유저이름",img,title,content,"",reserveTime,reserveDate, personCount,"방문예약")

                val networkService = (applicationContext as MyApplication).networkService
                val requestCall = networkService.doInsertReserve(reserve)
                requestCall.enqueue(object : Callback<ItemData> {
                    override fun onResponse(call: Call<ItemData>, response: Response<ItemData>) {
                        val intent= Intent(this@ReserveActivity,MainActivity::class.java)
                        startActivity(intent)
                    }

                    override fun onFailure(call: Call<ItemData>, t: Throwable) {
                        call.cancel()
                    }

                })
            }
        }

        binding.blank.setOnClickListener {
            reserveDate = binding.dateButton.text.toString()
            personCount = binding.person.text.toString()

            img = intent.getStringExtra("img")
            title = intent.getStringExtra("title")
            content = intent.getStringExtra("content")

            if(reserveDate == "") {
                Toast.makeText(this, "날짜를 입력하시오", Toast.LENGTH_SHORT).show()
            } else if(personCount == "") {
                Toast.makeText(this, "인원수를 입력하시오", Toast.LENGTH_SHORT).show()
            } else {
                var blank = BlankItem("유저이름", img, title, reserveDate, personCount, reserveTime, "빈자리 알림")

                val networkService = (applicationContext as MyApplication).networkService
                val requestCall = networkService.doInsertBlank(blank)
                requestCall.enqueue(object : Callback<BlankItem> {
                    override fun onResponse(call: Call<BlankItem>, response: Response<BlankItem>) {
                        val intent = Intent(this@ReserveActivity, MainActivity::class.java)

                        startActivity(intent)
                    }

                    override fun onFailure(call: Call<BlankItem>, t: Throwable) {
                        call.cancel()
                    }

                })
            }
        }

        binding.open.setOnClickListener {
            reserveDate = binding.dateButton.text.toString()
            personCount = binding.person.text.toString()

            img = intent.getStringExtra("img")
            title = intent.getStringExtra("title")
            content = intent.getStringExtra("content")

            if(reserveDate == "") {
                Toast.makeText(this, "날짜를 입력하시오", Toast.LENGTH_SHORT).show()
            } else if(personCount == "") {
                Toast.makeText(this, "인원수를 입력하시오", Toast.LENGTH_SHORT).show()
            } else {
                var blank = BlankItem("유저이름", img, title, reserveDate, personCount, reserveTime, "예약 오픈 알림")

                val networkService = (applicationContext as MyApplication).networkService
                val requestCall = networkService.doInsertBlank(blank)
                requestCall.enqueue(object : Callback<BlankItem> {
                    override fun onResponse(call: Call<BlankItem>, response: Response<BlankItem>) {
                        val intent = Intent(this@ReserveActivity, MainActivity::class.java)

                        startActivity(intent)
                    }

                    override fun onFailure(call: Call<BlankItem>, t: Throwable) {
                        call.cancel()
                    }

                })
            }
        }
    }

    // 달력 열어서 날짜 선택할때 필요한 함수
    private fun convertLongToDate(time: Long):String {

        val date = Date(time)
        val format = SimpleDateFormat(
            "yyyy.MM.dd",
            Locale.getDefault()
        )

        return format.format(date)
    }

    private fun showDatePicker() {
        val datePicker = MaterialDatePicker.Builder
            .datePicker()
            .setTitleText("예약 날짜를 선택하시오")
            .build()

        datePicker.show(
            supportFragmentManager,
            "date_picker"
        )

        datePicker.addOnPositiveButtonClickListener { datePicked ->
            date = datePicked

            binding.dateButton.text = convertLongToDate(datePicked)
        }
    }
}