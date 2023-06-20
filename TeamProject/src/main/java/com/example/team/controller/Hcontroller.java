package com.example.team.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.team.dto.Hvo;
import com.example.team.service.Hservice;

@RestController
@RequestMapping("/seat/heart/")
public class Hcontroller {

	@Autowired
	Hservice hservice;

	// 내가 올린 게시글을 좋아요 를 받거나 타인이 올린 게시글을 좋아요 누를 수 있다.
	// 내가 받은 총 좋아요 수 보여주기
	@GetMapping("/getList")
	public List<Hvo> getList(String h_id) {
		return hservice.getList(h_id);
	}

	// 다른 사람이 좋아요 누르면 카운트 됨.
	@GetMapping("/getCount")
	public int getCount(Integer h_num) {
		System.out.println("카운트: " + hservice.getCount(h_num));
		return hservice.getCount(h_num);
	}

	// 게시물 작성자의 총 좋아요 수 보여주기
	@GetMapping("/getList2")
	public List<Hvo> getList2(Integer h_num) {
		return hservice.getList2(h_num);
	}

	// 좋아요 +1 = 좋아요 클릭
	@PostMapping("/hRegister")
	public void hRegister(@RequestBody Hvo H) {
		System.out.println("좋아요 +1@@@@@@@@@");
		hservice.hRegister(H);
	}

	// 좋아요 -1 = 좋아요 짝수번 클릭
	@PostMapping("/hDelete")
	public void hDelete(@RequestBody Hvo H) {
		hservice.hDelete(H);
	}

	@GetMapping("/checked")
	public int hChecked(@Param("h_id") String h_id, @Param("h_num") Integer h_num) {
		return hservice.hChecked(h_id, h_num);
	}

}
