package com.example.teamproject.retrofit


import com.example.teamproject.model.ItemData
import com.example.teamproject.model.ItemDataList
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {

    @GET("main/myDining/myReserve")
    fun getMyReserve(@Query("r_username") name: String?): Call<ItemDataList>

}