package com.example.teamproject.retrofit

import com.example.teamproject.model.PageListModel
import com.example.teamproject.model.PageListModel2
import retrofit2.http.*


interface NetworkService {

    //https://apis.data.go.kr/1360000/TourStnInfoService1/getCityTourClmIdx1?serviceKey=인증키&pageNo=1&numOfRows=10&dataType=JSON&CURRENT_DATE=2018123110&DAY=3&CITY_AREA_ID=5013000000
    @GET("api/rstr")
    fun getList(
        @Query("serviceKey") serviceKey: String?,
    ): retrofit2.Call<PageListModel>


    @GET("api/rstr/img")
    fun getImgList(
        @Query("serviceKey") serviceKey: String?,
    ): retrofit2.Call<PageListModel2>

}