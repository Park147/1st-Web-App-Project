package com.example.team.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.team.dto.BookmarkVO;

public interface BookmarkService {

	public List<BookmarkVO> bookmarkL(String b_id);

	public void bookmarkR(BookmarkVO bookmark);

	public void bookmarkD(@Param("b_id") String b_id, @Param("b_name") String b_name);

}
