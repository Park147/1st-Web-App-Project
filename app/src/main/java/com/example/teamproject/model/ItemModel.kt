package com.example.teamproject.model

import com.google.gson.annotations.SerializedName

data class ItemModel (
    //식당id
    @SerializedName("RSTR_ID")
    var RSTR_ID: String,
    //식당이름
    @SerializedName("RSTR_NM")
    var RSTR_NM: String,
    //전화
    @SerializedName("RSTR_TELNO")
    var RSTR_TELNO: String,
    //업종
    @SerializedName("BSNS_STATM_BZCND_NM")
    var BSNS_STATM_BZCND_NM: String,
    //소개
    @SerializedName("RSTR_INTRCN_CONT")
    var RSTR_INTRCN_CONT: String,
    //도로명 주소
    @SerializedName("RSTR_RDNMADR")
    var RSTR_RDNMADR: String,
    //지번 주소
    @SerializedName("RSTR_LNNO_ADRES")
    var RSTR_LNNO_ADRES: String
)

