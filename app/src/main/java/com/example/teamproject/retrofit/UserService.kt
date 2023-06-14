package com.example.teamproject.retrofit

import com.example.teamproject.model.Member
import com.example.teamproject.model.ModInfo
import com.example.teamproject.model.ModPro
import com.example.teamproject.model.Rstr
import com.example.teamproject.model.RstrList
import retrofit2.Call
import retrofit2.http.*


interface UserService {

    //https://apis.data.go.kr/1360000/TourStnInfoService1/getCityTourClmIdx1?serviceKey=인증키&pageNo=1&numOfRows=10&dataType=JSON&CURRENT_DATE=2018123110&DAY=3&CITY_AREA_ID=5013000000
    @POST("/seat/member/register")
    fun memberregister(@Body member: Member): retrofit2.Call<Unit>

    @GET("/seat/member/getUser")
    fun getUser(
        @Query("m_id") m_id: String,
        @Query("m_password") m_password: String
    ): Call<Member>

    @GET("/seat/member/userPro")
    fun getPro(
        @Query("m_id")m_id: String
    ): Call<Member>

    @GET("/seat/member/userIntro")
    fun getIntro(
        @Query("m_id")m_id: String
    ): Call<Member>

    @POST("/seat/member/userPro")
    fun mUpPro(
        @Body member: ModPro
    ): Call<Unit>

    @POST("/seat/member/userIntro")
    fun mUpIntro(
        @Body member: ModInfo
    ): Call<Unit>

    @POST("/seat/member/userDelete")
    fun memberDelete(
        @Query("m_id")m_id: String
    ): Call<Unit>

    @GET("/seat/rstr/list")
    fun rstrList(): Call<List<Rstr>>

    @GET("seat/rstr/rstrinfo")
    fun getrstr(
        @Query("rstr_nm") rstr_nm: String
    ): Call<Rstr>
}