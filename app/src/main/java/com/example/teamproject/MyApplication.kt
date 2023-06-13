package com.example.teamproject

import android.app.Application
import com.example.teamproject.retrofit.UserService
import com.example.teamproject.retrofit.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application(){

    //add....................................
    var networkService: NetworkService
    var userService: UserService


    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("https://seoul.openapi.redtable.global/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val retrofit2: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("http://10.100.105.187:8888/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    init {
        networkService = retrofit.create(NetworkService::class.java)
        userService = retrofit2.create(UserService::class.java)
    }
}