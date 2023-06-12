package com.example.teamproject.retrofit


import com.example.teamproject.model.ItemData
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {

    @GET("reserve")
    fun reserve(@Query("r_id") r_id: String): Call<ItemData>

}