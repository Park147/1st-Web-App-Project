package com.example.teamproject

import android.app.Application
import com.example.teamproject.retrofit.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application(){

    //서비스 파일 불러오기
    var userService: UserService

    //스프링의 주소를 불러오기 [ 스프링과 안드로이드 연동을 위한 작업 ]
    val retrofit2: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("http://10.100.105.187:8888/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    init {
        //서비스파일 초기화하기
        userService = retrofit2.create(UserService::class.java)
    }
}