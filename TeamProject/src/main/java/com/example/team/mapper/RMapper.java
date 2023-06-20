package com.example.team.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.team.dto.Rvo;

@Repository
@Mapper
public interface RMapper {

	// 리뷰 전체목록
	public List<Rvo> getList();

	// 리뷰 나의 목록
	public List<Rvo> getMyList(String r_id);

	// 리뷰 등록하기
	public void rRegister(Rvo review);

	// 리뷰 하나 보여주기
	public Rvo rGet(Integer r_num);

	// 리뷰 수정하기
	public void rUpdate(Rvo review);

	// 리뷰 지우기
	public void rDelete(Integer r_num);

}