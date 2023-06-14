package com.example.teamproject

import android.app.Application
import com.example.teamproject.retrofit.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application(){

    var userService: UserService


    val retrofit2: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("http://10.100.105.187:8888/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    init {
        userService = retrofit2.create(UserService::class.java)
    }
}