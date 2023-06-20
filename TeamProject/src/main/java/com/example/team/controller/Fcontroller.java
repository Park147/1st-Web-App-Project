package com.example.team.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.team.dto.Fvo;
import com.example.team.service.Fservice;

@RestController
@RequestMapping("/seat/following/")
public class Fcontroller {

	@Autowired
	Fservice fservice;

	// fl 하기 , fr 당하기

	// 팔로잉 전체보여주기
	@GetMapping("/getFollowingList")
	public List<Fvo> getFollowingList(String fl_id) {
		return fservice.getFollowingList(fl_id);
	}

	@GetMapping("/getFollowingCount")
	public int getFollowingCount(String fl_id) {
		return fservice.getFollowingCount(fl_id);
	}

	// 팔로우 전체보여주기
	@GetMapping("/getFollowerList")
	public List<Fvo> getFollowerList(String fr_id) {
		return fservice.getFollowerList(fr_id);
	}

	@GetMapping("/getFollowerCount")
	public int getFollowerCount(String fr_id) {
		return fservice.getFollowerCount(fr_id);
	}

	// 팔로잉 리스트 +1 = 팔로우 클릭
	@PostMapping("/fRegister")
	public void fRegisterFollowing(@RequestBody Fvo follow) {
		System.out.println("팔로우등록@@@@@@@@@");
		fservice.fRegisterFollowing(follow);
	}

	// 팔로잉 리스트에서 -1 = 언팔로우 짝수번 클릭
	@PostMapping("/fDelete")
	public void fDeleteFollowing(@RequestBody Fvo follow) {
		System.out.println("팔로우 제거");
		fservice.fDeleteFollowing(follow);
	}

	@GetMapping("/fcheck")
	public int folcheck(@Param("fl_id") String fl_id, @Param("fr_id") String fr_id) {
		return fservice.fcheck(fl_id, fr_id);
	}

}
