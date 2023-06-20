package com.example.team.dto;

import lombok.Data;

@Data
public class MemberVO {

	private String m_id;
	private String m_password;
	private String m_password_con;
	private String m_name;
	private String m_gender;
	private String m_phone;
	private String m_address;
	private String m_nickname;
	private String m_introduction;
	private String m_activity_area;
	private String m_created_date;

}
