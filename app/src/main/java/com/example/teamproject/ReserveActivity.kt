package com.example.teamproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamproject.adapter.MyReserveAdapter
import com.example.teamproject.databinding.ActivityReserveBinding
import com.example.teamproject.databinding.ItemPersonRecyclerviewBinding
import com.example.teamproject.model.BlankItemList
import com.example.teamproject.model.ItemData
import com.example.teamproject.recycler.PersonCount
import com.google.android.material.datepicker.MaterialDatePicker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ReserveActivity : AppCompatActivity() {
    lateinit var binding: ActivityReserveBinding
    lateinit var bindingItem: ItemPersonRecyclerviewBinding
    lateinit var adapter: MyReserveAdapter
    var strDate:String =""
    var date:Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReserveBinding.inflate(layoutInflater)
        bindingItem = ItemPersonRecyclerviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val networkService = (applicationContext as MyApplication).networkService
        val reserveListCall = networkService.getBlank()

        reserveListCall.enqueue(object : Callback<BlankItemList> {
            override fun onResponse(call: Call<BlankItemList>, response: Response<BlankItemList>) {
                var item = response.body()?.blankItems

                var layoutManager = LinearLayoutManager(this@ReserveActivity)
                layoutManager.orientation = LinearLayoutManager.HORIZONTAL

                adapter = MyReserveAdapter( PersonCount(1))
                binding.personRecyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<BlankItemList>, t: Throwable) {
                call.cancel()
            }

        })

        binding.dateButton.setOnClickListener {
            showDatePicker()
        }

        binding.btn1.setOnClickListener {

            var reserveDate = binding.dateButton.text.toString()
            var personCount = ""
            var reserveTime = binding.btn1.text.toString()

            var reserve = ItemData("유저이름","","제목","","",reserveTime,reserveDate,personCount,"방문예약")

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

            var reserveDate = binding.dateButton.text.toString()
            var personCount = ""
            var reserveTime = binding.btn2.text.toString()

            var reserve = ItemData("유저이름","","제목","","",reserveTime,reserveDate,personCount,"방문예약")

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

            var reserveDate = binding.dateButton.text.toString()
            var personCount = ""
            var reserveTime = binding.btn3.text.toString()

            var reserve = ItemData("유저이름","","제목","","",reserveTime,reserveDate,personCount,"방문예약")

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