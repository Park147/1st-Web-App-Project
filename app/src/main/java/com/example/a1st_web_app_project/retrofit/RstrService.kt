package com.example.a1st_web_app_project.retrofit

import com.example.a1st_web_app_project.model.RstrModel
import com.example.a1st_web_app_project.model.randRstr
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface RstrService {


    @GET("/main/Rstr/list")
    fun getRstrList(): Call<List<RstrModel>>

    @GET("/main/Rstr/getRead")
    fun getRead(
        @Query("rstr_nm") rstr_nm: String
    ): Call<RstrModel>

    @GET("/main/Rstr/getrand")
    fun getRandList(): Call<List<randRstr>>

    @GET("/main/Rstr/getSearch")
    fun getSearch(
        @Query("rstr_nm") rstr_nm: String
    ): Call<List<RstrModel>>
    }
