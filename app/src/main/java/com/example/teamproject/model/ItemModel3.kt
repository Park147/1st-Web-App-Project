package com.example.teamproject.model

import com.google.gson.annotations.SerializedName

data class ItemModel3 (
    //식당id
    @SerializedName("RSTR_ID")
    var RSTR_ID: String,
    //이미지
    @SerializedName("RSTR_IMG_URL")
    var RSTR_IMG_URL: String,
    
)

