package com.example.team.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.team.dto.RstrVO;
import com.example.team.service.RstrService;

@RestController
@RequestMapping("/seat/rstr/")
public class RstrController {

	@Autowired
	RstrService rstrService;

	@GetMapping("/list")
	public List<RstrVO> getList() {
		System.out.println("리스트 출력");
		return rstrService.getList();
	}

	@GetMapping("rstrinfo")
	public RstrVO getRstr(@Param("rstr_nm") String rstr_nm) {
		return rstrService.getRstr(rstr_nm);
	}

	@PostMapping("upBook")
	public void upbookRstr(@RequestBody RstrVO rstr) {
		System.out.println("북뷰 업데이트");
		rstrService.upBook(rstr);
	}

}
