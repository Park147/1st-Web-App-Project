package com.example.a1st_web_app_project

import android.app.Application
import com.example.a1st_web_app_project.model.RstrModel
import com.example.a1st_web_app_project.retrofit.RstrService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyApplication: Application(){

    //add....................................
    var networkService: NetworkService
    var rstrService: RstrService
    val retrofit: Retrofit
        get() = Retrofit.Builder()
    .baseUrl(" https://seoul.openapi.redtable.global/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    init {
        networkService = retrofit.create(NetworkService::class.java)
        rstrService = retrofit2.create(RstrService::class.java)
    }
    val retrofit2: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("http://192.168.0.103:8090/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}