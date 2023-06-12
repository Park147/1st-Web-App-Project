package com.example.teamproject


import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("FestivalService/getFestivalKr")
    fun getList(
        @Query("serviceKey") serviceKey: String?,
        @Query("numOfRows") numOfRows: Int,
        @Query("pageNo") pageNo: Int,
        @Query("resultType") resultType : String
    ): retrofit2.Call<UserListModel>

}