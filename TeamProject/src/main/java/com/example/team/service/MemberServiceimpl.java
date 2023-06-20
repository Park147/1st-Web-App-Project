package com.example.team.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.team.dto.MemberVO;
import com.example.team.mapper.MemberMapper;

@Service
public class MemberServiceimpl implements MemberService {

	@Autowired
	MemberMapper memberMapper;

	@Override
	public MemberVO getUser(@Param("m_id") String m_id, @Param("m_password") String m_password) {
		return memberMapper.getUser(m_id, m_password);
	}

	@Override
	public int getCheck(@Param("m_id") String m_id, @Param("m_password") String m_password) {
		return memberMapper.getCheck(m_id, m_password);
	}

	@Override
	public MemberVO getData(String m_id) {
		return memberMapper.getData(m_id);
	}

	@Override
	public void registerUser(MemberVO member) {
		memberMapper.registerUser(member);

	}

	@Override
	public void deleteUser(String m_id) {
		memberMapper.deleteUser(m_id);
	}

	@Override
	public void updateProfile(MemberVO member) {
		memberMapper.updateProfile(member);
	}

	@Override
	public void updateIntro(MemberVO member) {
		memberMapper.updateIntro(member);
	}

}
