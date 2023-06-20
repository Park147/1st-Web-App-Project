package com.example.team.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.team.dto.Hvo;

@Repository
@Mapper
public interface HMapper {

	// 내가 올린 게시글을 좋아요 를 받거나 타인이 올린 게시글을 좋아요 누를 수 있다.
	// 내가 받은 총 좋아요 수 보여주기
	public List<Hvo> getList(String h_id);

	// 다른 사람이 좋아요 누르면 카운트 됨.
	public int getCount(Integer h_num);

	// 게시물 작성자의 총 좋아요 수 보여주기
	public List<Hvo> getList2(Integer h_num);

	// 좋아요 +1 = 좋아요 클릭
	public void hRegister(Hvo heart);

	// 좋아요 -1 = 좋아요 짝수번 클릭
	public void hDelete(Hvo heart);

	public int hChecked(@Param("h_id") String h_id, @Param("h_num") Integer h_num);

}