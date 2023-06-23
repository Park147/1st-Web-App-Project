package com.example.teamproject.model

// Member 의 객체 [ 로그인 회원가입을 위한 정보 ]
data class Member(
    var m_id: String,
    var m_password: String,
    var m_password_con: String,
    var m_name: String,
    var m_gender: String,
    var m_phone: String,
    var m_address: String,
    var m_nickname: String,
    var m_introduction: String,
    var m_activity_area: String,
    var m_created_date: String
)
