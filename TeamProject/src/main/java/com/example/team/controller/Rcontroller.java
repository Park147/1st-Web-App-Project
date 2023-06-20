package com.example.team.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.team.dto.Rvo;
import com.example.team.service.Rservice;

@RestController
@RequestMapping("/seat/review/")
public class Rcontroller {

	@Autowired
	Rservice rservice;

	@GetMapping("/getList")
	public List<Rvo> getList() {
		return rservice.getList();
	}

	@GetMapping("/getMyList")
	public List<Rvo> getMyList(String r_id) {
		return rservice.getMyList(r_id);
	}

	@PostMapping("/rRegister")
	public void rRegister(@RequestBody Rvo R) {
		System.out.println("등록중@@@@@@@@@");
		rservice.rRegister(R);
	}

	@GetMapping("/rGet")
	public Rvo rGet(@Param("r_num") Integer r_num) {
		return rservice.rGet(r_num);
	}

	@PostMapping("/rUpdate")
	public void rUpdate(@RequestBody Rvo review) {
		rservice.rUpdate(review);
	}

	@PostMapping("/rDelete")
	public void rDelete(@Param("r_num") Integer r_num) {
		rservice.rDelete(r_num);
	}

}
