package com.example.team.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.team.dto.Hvo;
import com.example.team.mapper.HMapper;

@Service
public class Hserviceimpl implements Hservice {

	@Autowired
	HMapper hmapper;
	// 내가 올린 게시글을 좋아요 를 받거나 타인이 올린 게시글을 좋아요 누를 수 있다.

	// 내가 받은 총 좋아요 수 보여주기
	@Override
	public List<Hvo> getList(String h_id) {
		return hmapper.getList(h_id);
	}

	// 다른 사람이 좋아요 누르면 카운트 됨.
	@Override
	public int getCount(Integer h_num) {
		return hmapper.getCount(h_num);
	}

	// 게시글 작성자가 받은 총 좋아요 수 보여주기
	@Override
	public List<Hvo> getList2(Integer h_num) {
		return hmapper.getList2(h_num);
	}

	// 좋아요 +1
	@Override
	public void hRegister(Hvo heart) {
		hmapper.hRegister(heart);
	}

	// 좋아요 -1
	@Override
	public void hDelete(Hvo heart) {
		hmapper.hDelete(heart);
	}

	@Override
	public int hChecked(@Param("h_id") String h_id, @Param("h_num") Integer h_num) {
		return hmapper.hChecked(h_id, h_num);
	}

}
