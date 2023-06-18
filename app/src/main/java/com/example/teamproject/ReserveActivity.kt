package com.example.teamproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.teamproject.databinding.ActivityReserveBinding
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
    var personCount:String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReserveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dateButton.setOnClickListener {
            showDatePicker()
        }

        binding.btn1.setOnClickListener {

            reserveDate = binding.dateButton.text.toString()
            reserveTime = binding.btn1.text.toString()
            personCount = binding.person.text.toString()

            var reserve = ItemData("유저이름","","제목","","",reserveTime,reserveDate, personCount!!,"방문예약")

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

        binding.btn2.setOnClickListener {

            reserveDate = binding.dateButton.text.toString()
            reserveTime = binding.btn2.text.toString()
            personCount = binding.person.text.toString()

            var reserve = ItemData("유저이름","","제목","","",reserveTime,reserveDate, personCount!!,"방문예약")

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

        binding.btn3.setOnClickListener {

            reserveDate = binding.dateButton.text.toString()
            reserveTime = binding.btn2.text.toString()
            personCount = binding.person.text.toString()

            var reserve = ItemData("유저이름","","제목","","",reserveTime,reserveDate, personCount!!,"방문예약")

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