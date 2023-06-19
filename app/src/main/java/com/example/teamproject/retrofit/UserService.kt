package com.example.teamproject.retrofit

import com.example.teamproject.model.Bookmark
import com.example.teamproject.model.Heart
import com.example.teamproject.model.Member
import com.example.teamproject.model.ModInfo
import com.example.teamproject.model.ModPro
import com.example.teamproject.model.Pimg
import com.example.teamproject.model.Review
import com.example.teamproject.model.Review_r
import com.example.teamproject.model.Rstr
import com.example.teamproject.model.Rstrbook
import retrofit2.Call
import retrofit2.http.*


// 스프링의 Controller 와 같은 구간으로 스프링과 안드로이드 연동을 위한 서비스 구간
interface UserService {

    
    // 로그인 및 회원가입
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

    
    // 즐겨찾기
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

    
    // 리뷰목록
    @GET("/seat/review/getList")
    fun getRList(): Call<List<Review>>

    // 내 게시물 보여주기
    @GET("/seat/review/getMyList")
    fun getmyList(
    ): Call<List<Review>>

    // 게시물 등록
    @POST("/seat/review/rRegister")
    fun viewRg(
        @Body review: Review_r
    ): Call<Unit>

    // 게시물 출력
    @GET("/seat/review/rGet")
    fun viewGet(
        @Query("r_num") r_num: Int
    ): Call<Review>

    //게시물 수정
    @POST("/seat/review/rUpdate")
    fun viewUpdate(
        @Body review: Review
    ): Call<Unit>

    //게시물 제거
    @POST("/seat/review/rDelete")
    fun viewDelete(
        @Query("r_num") r_num: Int
    ): Call<Unit>

    //좋아요
    @GET("/seat/heart/getList")
    fun heartList(
        @Query("h_id")h_id: String
    ): Call<List<Heart>>

    @GET("/seat/heart/getCount")
    fun heartCount(
        @Query("h_num") h_num: Int
    ): Call<Int>

    @GET("/seat/heart/getList2")
    fun heartList2(
        @Query("h_num")h_num: Int
    ): Call<List<Heart>>

    @POST("/seat/heart/hRegister")
    fun heartR(
        @Body heart: Heart
    ): Call<Unit>

    @POST("/seat/heart/hDelete")
    fun heartD(
        @Body heart: Heart
    ): Call<Unit>

    @GET("/seat/heart/checked")
    fun checkedH(
        @Query("h_id") h_id: String,
        @Query("h_num")h_num: Int
    ): Call<Int>
}