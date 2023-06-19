package com.example.teamproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.teamproject.databinding.ActivityReserveUpdateBinding
import com.example.teamproject.model.ItemData
import com.google.android.material.datepicker.MaterialDatePicker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ReserveUpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityReserveUpdateBinding
    var date:Long = 0L
    var reserveDate:String = ""
    var reserveTime:String = ""
    var personCount:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReserveUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //날짜 선택하는 달력 호출
        binding.dateButton.setOnClickListener {
            showDatePicker()
        }

        binding.cancel.setOnClickListener {
            reserveDate = binding.dateButton.text.toString()
            personCount = binding.person.text.toString()
            var reserve = ItemData("유저이름","","제목2","","","",reserveDate, personCount,"방문취소")

            val networkService = (applicationContext as MyApplication).networkService
            val reserveDeleteCall = networkService.update(reserve)

            reserveDeleteCall.enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    Toast.makeText(this@ReserveUpdateActivity,"success", Toast.LENGTH_SHORT).show()
                    val intent= Intent(this@ReserveUpdateActivity, MyDining::class.java)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Toast.makeText(this@ReserveUpdateActivity,"fail", Toast.LENGTH_SHORT).show()
                }

            })
        }

        // 예약날짜, 인원수 입력안하면 Toast호출, 예약시간 클릭하면 db에 값 저장하고 메인으로 넘어감
        binding.btn1.setOnClickListener {

            reserveDate = binding.dateButton.text.toString()
            reserveTime = binding.btn1.text.toString()
            personCount = binding.person.text.toString()

            if(reserveDate == "") {
                Toast.makeText(this, "날짜를 입력하시오", Toast.LENGTH_SHORT).show()
            } else if(personCount == "") {
                Toast.makeText(this, "인원수를 입력하시오", Toast.LENGTH_SHORT).show()
            } else {
                var reserve = ItemData("유저이름","","제목1","","",reserveTime,reserveDate, personCount,"방문예약")

                val networkService = (applicationContext as MyApplication).networkService
                val reserveDeleteCall = networkService.update(reserve)

                reserveDeleteCall.enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        Toast.makeText(this@ReserveUpdateActivity,"success", Toast.LENGTH_SHORT).show()
                        val intent= Intent(this@ReserveUpdateActivity, MyDining::class.java)
                        startActivity(intent)
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        Toast.makeText(this@ReserveUpdateActivity,"fail", Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }

        // 예약날짜, 인원수 입력안하면 Toast호출, 예약시간 클릭하면 db에 값 저장하고 메인으로 넘어감
        binding.btn2.setOnClickListener {

            reserveDate = binding.dateButton.text.toString()
            reserveTime = binding.btn2.text.toString()
            personCount = binding.person.text.toString()
            if(reserveDate == "") {
                Toast.makeText(this, "날짜를 입력하시오", Toast.LENGTH_SHORT).show()
            } else if(personCount == "") {
                Toast.makeText(this, "인원수를 입력하시오", Toast.LENGTH_SHORT).show()
            } else {
                var reserve = ItemData("유저이름","","제목2","","",reserveTime,reserveDate, personCount,"방문예약")

                val networkService = (applicationContext as MyApplication).networkService
                val reserveDeleteCall = networkService.update(reserve)

                reserveDeleteCall.enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        Toast.makeText(this@ReserveUpdateActivity,"success", Toast.LENGTH_SHORT).show()
                        val intent= Intent(this@ReserveUpdateActivity, MyDining::class.java)
                        startActivity(intent)
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        Toast.makeText(this@ReserveUpdateActivity,"fail", Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }

        // 예약날짜, 인원수 입력안하면 Toast호출, 예약시간 클릭하면 db에 값 저장하고 메인으로 넘어감
        binding.btn3.setOnClickListener {

            reserveDate = binding.dateButton.text.toString()
            reserveTime = binding.btn3.text.toString()
            personCount = binding.person.text.toString()
            if(reserveDate == "") {
                Toast.makeText(this, "날짜를 입력하시오", Toast.LENGTH_SHORT).show()
            } else if(personCount == "") {
                Toast.makeText(this, "인원수를 입력하시오", Toast.LENGTH_SHORT).show()
            } else {
                var reserve = ItemData("유저이름","","제목3","","",reserveTime,reserveDate, personCount,"방문예약")

                val networkService = (applicationContext as MyApplication).networkService
                val reserveDeleteCall = networkService.update(reserve)

                reserveDeleteCall.enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        Toast.makeText(this@ReserveUpdateActivity,"success", Toast.LENGTH_SHORT).show()
                        val intent= Intent(this@ReserveUpdateActivity, MyDining::class.java)
                        startActivity(intent)
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        Toast.makeText(this@ReserveUpdateActivity,"fail", Toast.LENGTH_SHORT).show()
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