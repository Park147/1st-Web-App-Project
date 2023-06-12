package com.example.a1st_web_app_project


import com.google.gson.annotations.SerializedName

data class ItemModel (
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

