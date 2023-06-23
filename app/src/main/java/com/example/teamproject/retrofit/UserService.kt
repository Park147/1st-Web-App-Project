package com.example.teamproject.retrofit

import com.example.teamproject.model.BlankItem
import com.example.teamproject.model.BlankItemList
import com.example.teamproject.model.Bookmark
import com.example.teamproject.model.Comment
import com.example.teamproject.model.CommentR
import com.example.teamproject.model.CommentU
import com.example.teamproject.model.Follow
import com.example.teamproject.model.Heart
import com.example.teamproject.model.ItemData
import com.example.teamproject.model.ItemDataList
import com.example.teamproject.model.Member
import com.example.teamproject.model.ModInfo
import com.example.teamproject.model.ModPro
import com.example.teamproject.model.Pimg
import com.example.teamproject.model.Review
import com.example.teamproject.model.ReviewU
import com.example.teamproject.model.Review_r
import com.example.teamproject.model.Rstr
import com.example.teamproject.model.RstrModel
import com.example.teamproject.model.randRstr
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

    @GET("seat/member/getCheck")
    fun getCheck(
        @Query("m_id") m_id: String,
        @Query("m_password") m_password: String
    ): Call<Int>

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

    
    // 즐겨찾기
    @GET("seat/bookmark/list")
    fun bmList(
        @Query("b_id") b_id: String,
    ): Call<List<Bookmark>>

    @POST("seat/bookmark/register")
    fun bmregister(
        @Body bookmark: Bookmark
    ): Call<Unit>

    @GET("seat/bookmark/check")
    fun bookcheck(
        @Query("b_id") b_id: String,
        @Query("b_name") b_name: String
    ): Call<Int>

    @POST("seat/bookmark/delete")
    fun bmdelete(
        @Query("b_id") b_id: String,
        @Query("b_name") b_name: String
    ): Call<Unit>

    
    // 리뷰목록
    @GET("/seat/review/getList")
    fun getRList(): Call<List<Review>>

    // 내 게시물 보여주기
    @GET("/seat/review/getMyList")
    fun getmyList(
        @Query("r_id") r_id: String
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
        @Body review: ReviewU
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

    
    // 팔로우 작업
    @GET("/seat/following/getFollowingList")
    fun followList(
        @Query("fl_id") fl_id: String
    ): Call<List<Follow>>

    @GET("/seat/following/getFollowerList")
    fun followingList(
        @Query("fr_id") fr_id: String
    ): Call<List<Follow>>

    @POST("/seat/following/fRegister")
    fun followR(
        @Body follow: Follow
    ): Call<Unit>

    @POST("/seat/following/fDelete")
    fun followD(
        @Body follow: Follow
    ): Call<Unit>

    @GET("/seat/following/fcheck")
    fun followC(
        @Query("fl_id")fl_id: String,
        @Query("fr_id")fr_id: String
    ): Call<Int>

    @GET("/seat/following/getFollowingCount")
    fun followingC(
        @Query("fl_id")fl_id: String,
    ): Call<Int>

    @GET("/seat/following/getFollowerCount")
    fun followerC(
        @Query("fr_id")fr_id: String
    ): Call<Int>

    // 댓글 작업
    @GET("/seat/comment/getList")
    fun cgetList(
        @Query("c_getnum") c_getnum: Int
    ): Call<List<Comment>>

    @POST("/seat/comment/cRegister")
    fun commentR(
        @Body commentR: CommentR
    ): Call<Unit>

    @GET("/seat/comment/cGet")
    fun commentG(
        @Query("c_num")c_num: Int
    ): Call<Comment>

    @POST("/seat/comment/cUpdate")
    fun commentU(
        @Body commentU: CommentU
    ): Call<Unit>

    @POST("/seat/comment/cDelete")
    fun commentD(
        @Query("c_num")c_num: Int
    ): Call<Unit>

    @GET("/seat/comment/cCount")
    fun commentC(
        @Query("c_getnum")c_getnum: Int
    ): Call<Int>

    @GET("main/myDining/myWaiting")
    fun getMyWaiting(@Query("r_username") r_username: String?): Call<ItemData>

    @GET("main/myDining/waitingAll")
    fun getWaitingAll(): Call<ItemDataList>

    @POST("main/myDining/delete/{w_title}")
    fun  deleteWaitingList(@Path("w_title")w_title:String?):Call<Unit>

    @GET("main/myDining/myBlank")
    fun getMyBlank(@Query("r_username") r_username: String?): Call<BlankItem>

    @GET("main/myDining/blank")
    fun getBlank(): Call<BlankItemList>

    @POST("main/myDining/blank/delete/{b_title}")
    fun  deleteBlankList(@Path("b_title")b_title:String?):Call<Unit>

    @POST("main/myDining/insert")
    fun doInsertReserve(@Body reserve: ItemData?): Call<ItemData>

    @POST("main/myDining/insertBlank")
    fun doInsertBlank(@Body blank: BlankItem?): Call<BlankItem>

    @POST("main/myDining/update")
    fun update(@Body reserve: ItemData?):Call<Unit>

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

    //검색

    @GET("/main/Rstr/getarea")
    fun getArea(
        @Query("rstr_addr") rstr_addr: String
    ): Call<List<RstrModel>>

    @GET("/main/Rstr/gettype")
    fun getType(
        @Query("rstr_list") rstr_list: String
    ): Call<List<RstrModel>>

    @GET("/main/Rstr/getname")
    fun getName(
        @Query("rstr_nm") rstr_nm: String
    ): Call<List<RstrModel>>

    @GET("/main/Rstr/getareacount")
    fun getAreacount(
        @Query("rstr_addr") rstr_addr: String
    ): Call<Int>

    @GET("/main/Rstr/gettypecount")
    fun getTypecount(
        @Query("rstr_list") rstr_list: String
    ): Call<Int>

    @GET("/main/Rstr/getnamecount")
    fun getNamecount(
        @Query("rstr_nm") rstr_nm: String
    ): Call<Int>
}