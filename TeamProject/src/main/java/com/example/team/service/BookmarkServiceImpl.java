package com.example.team.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.team.dto.BookmarkVO;
import com.example.team.mapper.BookmarkMapper;

@Service
public class BookmarkServiceImpl implements BookmarkService {

	@Autowired
	BookmarkMapper bookmarkMapper;

	@Override
	public List<BookmarkVO> bookmarkL(String b_id) {
		return bookmarkMapper.bookmarkL(b_id);
	}

	@Override
	public void bookmarkR(BookmarkVO bookmark) {
		bookmarkMapper.bookmarkR(bookmark);
	}

	@Override
	public void bookmarkD(@Param("b_id") String b_id, @Param("b_name") String b_name) {
		bookmarkMapper.bookmarkD(b_id, b_name);
	}

}
