package com.example.teamproject

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.multidex.MultiDexApplication
import com.example.teamproject.retrofit.NetworkService


class MyApplication : MultiDexApplication() {

    var networkService: NetworkService
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("http://10.100.105.6:8088/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    init {
        networkService = retrofit.create(NetworkService::class.java)
    }
}