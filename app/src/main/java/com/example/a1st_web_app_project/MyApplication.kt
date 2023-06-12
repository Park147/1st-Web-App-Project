package com.example.a1st_web_app_project

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application(){

    //add....................................
    var networkService: NetworkService

    val retrofit: Retrofit
        get() = Retrofit.Builder()


    .baseUrl(" https://seoul.openapi.redtable.global/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    init {
        networkService = retrofit.create(NetworkService::class.java)
    }
}