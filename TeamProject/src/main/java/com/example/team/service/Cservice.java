package com.example.team.service;

import java.util.List;

import com.example.team.dto.Cvo;

public interface Cservice {

	// 댓글 전체목록
	public List<Cvo> getList(Integer c_getnum);

	// 댓글 등록하기
	public void cRegister(Cvo comment);

	// 댓글 하나 보여주기
	public Cvo cGet(Integer c_num);

	// 댓글 수정하기
	public void cUpdate(Cvo comment);

	// 댓글 삭제
	public void cDelete(Integer c_num);

	public int getCount(Integer c_getnum);

}