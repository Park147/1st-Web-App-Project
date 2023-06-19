package com.example.teamproject

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.teamproject.retrofit.NetworkService


class MyApplication : Application() {

    var networkService: NetworkService
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("http://10.100.105.6:8088/")
//            .baseUrl("http://192.168.0.175:8088/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    init {
        networkService = retrofit.create(NetworkService::class.java)
    }
}