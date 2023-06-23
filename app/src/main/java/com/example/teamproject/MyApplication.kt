package com.example.teamproject

import android.app.Application
import com.example.teamproject.retrofit.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyApplication : Application() {

    var userService: UserService
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("http://10.100.105.6:8088/")
//            .baseUrl("http://192.168.0.175:8088/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    init {
        userService = retrofit.create(UserService::class.java)
    }
}