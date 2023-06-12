package com.example.a1st_web_app_project

import com.google.gson.annotations.SerializedName

data class PageListModel (
    //var data: List<ItemModel>?
    var body: List<ItemModel2>
)

data class ItemModel2 (
    @SerializedName("RSTR_NM")
    var RSTR_NM: String,
    @SerializedName("RSTR_TELNO")
    var RSTR_TELNO: String,
    @SerializedName("RSTR_RDNMADR")
    var RSTR_RDNMADR: String,
    @SerializedName("BSNS_STATM_BZCND_NM")
    var BSNS_STATM_BZCND_NM: String,
    @SerializedName("RSTR_INTRCN_CONT")
    var RSTR_INTRCN_CONT: String

)