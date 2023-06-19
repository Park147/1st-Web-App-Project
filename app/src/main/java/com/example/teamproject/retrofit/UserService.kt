package com.example.teamproject.retrofit

import com.example.teamproject.model.Bookmark
import com.example.teamproject.model.Member
import com.example.teamproject.model.ModInfo
import com.example.teamproject.model.ModPro
import com.example.teamproject.model.Pimg
import com.example.teamproject.model.Rstr
import com.example.teamproject.model.Rstrbook
import retrofit2.Call
import retrofit2.http.*


// 스프링의 Controller 와 같은 구간으로 스프링과 안드로이드 연동을 위한 서비스 구간
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

    @POST("seat/rstr/upBook")
    fun upbookview(
        @Body rstrbook: Rstrbook
    ): Call<Unit>

    @GET("seat/bookmark/list")
    fun bmList(
        @Query("b_id") b_id: String,
    ): Call<List<Bookmark>>

    @POST("seat/bookmark/register")
    fun bmregister(
        @Body bookmark: Bookmark
    ): Call<Unit>

    @POST("seat/bookmark/delete")
    fun bmdelete(
        @Query("b_id") b_id: String,
        @Query("b_name") b_name: String
    ): Call<Unit>

    @POST("seat/img/save")
    fun imgsave(
        @Body pimg: Pimg
    ): Call<Unit>

    @GET("seat/img/selimg")
    fun selimg(
        @Query("idx") idx: String
    ): Call<Pimg>
}