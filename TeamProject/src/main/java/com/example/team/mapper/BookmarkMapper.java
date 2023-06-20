package com.example.team.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.team.dto.BookmarkVO;

@Mapper
public interface BookmarkMapper {

	public List<BookmarkVO> bookmarkL(String b_id);

	public void bookmarkR(BookmarkVO bookmark);

	public void bookmarkD(@Param("b_id") String b_id, @Param("b_name") String b_name);

	public int bookmarkC(@Param("b_id") String b_id, @Param("b_name") String b_name);

}
