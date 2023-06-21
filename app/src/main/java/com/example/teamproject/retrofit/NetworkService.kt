package com.example.teamproject.retrofit


import com.example.teamproject.model.BlankItem
import com.example.teamproject.model.BlankItemList
import com.example.teamproject.model.ItemData
import com.example.teamproject.model.ItemDataList
import com.example.teamproject.model.RstrModel
import com.example.teamproject.model.randRstr
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {

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
}