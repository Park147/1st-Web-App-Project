package com.example.team.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.team.dto.MemberVO;
import com.example.team.service.MemberService;

@RestController
@RequestMapping("/seat/member/")
public class MemberController {

	@Autowired
	MemberService memberService;

	@PostMapping("/register")
	public void register(@RequestBody MemberVO member) {
		memberService.registerUser(member);
		System.out.print("11");
	}

	@GetMapping("/getUser")
	public MemberVO getUser(@Param("m_id") String m_id, @Param("m_password") String m_password) {
		System.out.println("로그인 테스트ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
		MemberVO member = memberService.getUser(m_id, m_password);
		return member;
	}

	@GetMapping("/getCheck")
	public int getCheck(@Param("m_id") String m_id, @Param("m_password") String m_password) {
		return memberService.getCheck(m_id, m_password);
	}

	@GetMapping({ "/userPro", "/userIntro" })
	public MemberVO doGetData(@Param("m_id") String m_id) {
		MemberVO member = memberService.getData(m_id);
		return member;
	}

	@PostMapping("/userPro")
	public void userProfile(@RequestBody MemberVO member) {
		System.out.println("정보수정 테스트ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
		memberService.updateProfile(member);
	}

	@PostMapping("/userIntro")
	public void userIntro(@RequestBody MemberVO member) {
		memberService.updateIntro(member);
	}

	@PostMapping("/userDelete")
	public void userDelete(@Param("m_id") String m_id) {
		memberService.deleteUser(m_id);
	}

}
