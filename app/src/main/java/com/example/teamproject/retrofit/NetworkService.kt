package com.example.teamproject.retrofit


import com.example.teamproject.model.ItemData
import com.example.teamproject.model.ItemDataList
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {

    @GET("main/myDining/myReserve")
    fun getMyReserve(@Query("r_username") r_username: String?): Call<ItemData>

    @GET("main/myDining/reserve")
    fun getReserve(): Call<ItemDataList>

    @POST("main/myDining/insert")
    fun doInsertReserve(@Body item: ItemData?): Call<ItemData>
}