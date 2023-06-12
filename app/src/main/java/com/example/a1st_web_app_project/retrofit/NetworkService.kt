package com.example.a1st_web_app_project



import com.example.a1st_web_app_project.PageListModel
import retrofit2.Call
import retrofit2.http.*


interface NetworkService {


    //공연 정보
    // itemModel, PageListModel 참고
//    http://apis.data.go.kr/6260000/BusanCultureThemeService/getBusanCultureTheme?serviceKey=인증키&numOfRows=10&pageNo=1
    @GET("api/rstr")
    fun getList(
        @Query("serviceKey") serviceKey: String?,
    ): retrofit2.Call<PageListModel>


}