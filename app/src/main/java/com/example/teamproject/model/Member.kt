package com.example.teamproject.model

import java.util.Date

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
    var m_created_date: Date
)
