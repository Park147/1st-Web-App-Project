package com.example.team.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.team.dto.MemberVO;

@Repository
@Mapper
public interface MemberMapper {

	public MemberVO getUser(@Param("m_id") String m_id, @Param("m_password") String m_password);

	public MemberVO getData(String m_id);

	public void registerUser(MemberVO member);

	public void deleteUser(String m_id);

	public void updateProfile(MemberVO member);

	public void updateIntro(MemberVO member);

}
