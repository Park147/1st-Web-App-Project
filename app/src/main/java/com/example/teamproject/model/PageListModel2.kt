package com.example.teamproject.model

import com.google.gson.annotations.SerializedName

//{
//    "header": {
//    "resultCode": "00",
//    "resultMsg": "NORMAL_SERVICE",
//    "numOfRows": 1000,
//    "pageNo": 2,
//    "totalCount": 167659
//},
//    "body": [
data class PageListModel2 (
    var body: List<ItemModel4>
)

data class ItemModel4(
    //식당 아이디
    @SerializedName("RSTR_ID")
    var RSTR_ID: String,
    //식당이름
    @SerializedName("RSTR_NM")
    var RSTR_NM: String,
    //이미지
    @SerializedName("RSTR_IMG_URL")
    var RSTR_IMG_URL: String,


)