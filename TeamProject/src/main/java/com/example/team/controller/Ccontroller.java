package com.example.team.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.team.dto.Cvo;
import com.example.team.service.Cservice;

@RestController
@RequestMapping("/seat/comment/")
public class Ccontroller {

	@Autowired
	Cservice cservice;

	@GetMapping("/getList")
	public List<Cvo> getList(Integer c_getnum) {
		return cservice.getList(c_getnum);
	}

	@PostMapping("/cRegister")
	public void cRegister(@RequestBody Cvo comment) {
		System.out.println("댓글등록@@@@@@@@@");
		cservice.cRegister(comment);
	}

	@GetMapping("/cGet")
	public Cvo cGet(@Param("c_num") Integer c_num) {
		return cservice.cGet(c_num);
	}

	@PostMapping("/cUpdate")
	public void cUpdate(@RequestBody Cvo comment) {
		cservice.cUpdate(comment);
	}

	@PostMapping("/cDelete")
	public void cDelete(@Param("c_num") Integer c_num) {
		cservice.cDelete(c_num);
	}

	@GetMapping("/cCount")
	public int cCount(Integer c_getnum) {
		return cservice.getCount(c_getnum);
	}

}
