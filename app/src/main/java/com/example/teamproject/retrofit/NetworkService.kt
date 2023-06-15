package com.example.teamproject.retrofit


import com.example.teamproject.model.ItemData
import com.example.teamproject.model.ItemDataList
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {

    @GET("main/myDining/myWaiting")
    fun getMyWaiting(@Query("r_username") r_username: String?): Call<ItemData>

    @GET("main/myDining/waiting")
    fun getWaiting(): Call<ItemDataList>

    @POST("main/myDining/delete/{w_title}")
    fun  deleteWaitingList(@Path("w_title")w_title:String?):Call<Unit>
}