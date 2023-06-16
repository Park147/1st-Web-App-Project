package com.example.team.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.team.dto.BookmarkVO;
import com.example.team.service.BookmarkService;

@RestController
@RequestMapping("/seat/bookmark")
public class BookmarkController {

	@Autowired
	BookmarkService bookmarkService;

	@GetMapping("/list")
	public List<BookmarkVO> bookmarkList(String b_id) {
		return bookmarkService.bookmarkL(b_id);
	}

	@PostMapping("/register")
	public void bookmarkRegister(@RequestBody BookmarkVO bookmark) {
		System.out.println("북마크 등록");
		bookmarkService.bookmarkR(bookmark);
	}

	@PostMapping("/delete")
	public void bookmarkdelete(@Param("b_id") String b_id, @Param("b_name") String b_name) {
		bookmarkService.bookmarkD(b_id, b_name);
	}
}
